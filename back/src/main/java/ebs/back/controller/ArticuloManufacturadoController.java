package ebs.back.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.ArticuloManufacturado;
import ebs.back.entity.Insumo;
import ebs.back.entity.Receta;
import ebs.back.entity.Stock;
import ebs.back.service.ArticuloManufacturadoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/manufacturado")
public class ArticuloManufacturadoController
		extends BaseController<ArticuloManufacturado, ArticuloManufacturadoService> {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * 
	 * @param idManufacturado
	 * @return True: Si un Manufacturado puede elaborarse, teniendo en cuenta si hay
	 *         stock suficiente de sus insumos
	 */
	/*
	 * @GetMapping("/stockManufacturado/{id}") public boolean
	 * hayStockManufacturado(@PathVariable Long idManufacturado) {
	 * 
	 * ObjectMapper mapper = new
	 * ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	 * mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	 * Object response = this.getOne(idManufacturado).getBody();
	 * 
	 * String responseJson = ""; try { responseJson =
	 * mapper.writeValueAsString(response); } catch (JsonProcessingException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * ArticuloManufacturadoWrapper manufacturadoWrapper = new
	 * ArticuloManufacturadoWrapper(); try { manufacturadoWrapper =
	 * mapper.readValue(responseJson, ArticuloManufacturadoWrapper.class); } catch
	 * (JsonProcessingException e) { e.printStackTrace(); }
	 * 
	 * manufacturadoWrapper.setRecetas(this.getRecetasXManufacturado(idManufacturado
	 * )); return this.verificarStock(manufacturadoWrapper); }
	 */

	/**
	 * 
	 * @param id
	 * @return Todas las recetas de un Manufacturado,
	 */
	@GetMapping("/recetasManufacturado/{id}")
	public List<Receta> getRecetasXManufacturado(@PathVariable Long id) {
		List<Receta> recetas = this.jdbcTemplate.query(
				"SELECT r.idReceta, r.cantidadInsumo, i.idInsumo, i.denominacion, i.unidadMedida, s.actual "
						+ "FROM stock s INNER JOIN insumo i ON s.idStock = i.idStock INNER JOIN receta r "
						+ "ON i.idInsumo = r.idInsumo WHERE r.idManufacturado = " + id,

				new RowMapper<Receta>() {
					@Override
					public Receta mapRow(ResultSet rs, int rowNum) throws SQLException {
						Receta receta = new Receta();
						receta.setId(rs.getLong("r.idReceta"));
						receta.setCantidadInsumo(rs.getFloat("r.cantidadInsumo"));

						Insumo insumo = new Insumo();
						insumo.setIdInsumo(rs.getLong("i.idInsumo"));
						insumo.setDenominacion(rs.getString("i.denominacion"));
						insumo.setUnidadMedida(rs.getString("i.unidadMedida"));

						Stock stock = new Stock();
						stock.setActual(rs.getLong("s.actual"));
						insumo.setStock(stock);
						receta.setInsumo(insumo);
						return receta;
					}
				});

		return recetas;
	}

	/**
	 * 
	 * @param idInsumo
	 * @return Precio unitario más actual de un insumo
	 */
	public Float getPrecioUnitario(Long idInsumo) {
		return this.jdbcTemplate
				.queryForObject("SELECT precioUnitario FROM historialcompraaproveedores WHERE idInsumo =" + idInsumo
						+ " ORDER BY fechaCompra DESC LIMIT 1", Float.class);
	}

	@GetMapping("/costo")
	public Float getCosto(@RequestParam String idsInsumosStr, @RequestParam String cantInsumo) {
		List<String> idsAuxList = Arrays.asList(idsInsumosStr.split(","));
		List<String> cantInsumosAuxList = Arrays.asList(cantInsumo.split(","));
		List<Long> idsInsumos = idsAuxList.stream().map(Long::parseLong).collect(Collectors.toList());
		List<Float> cantInsumoList = cantInsumosAuxList.stream().map(Float::parseFloat).collect(Collectors.toList());
		
		List<Float> costosInsumo = idsInsumos.stream().map(id -> this.getPrecioUnitario(id)).collect(Collectors.toList());
		Float sumatoria=0.0F;
		for(int i=0; i<costosInsumo.size();i++) {			
			sumatoria += cantInsumoList.get(i) * costosInsumo.get(i);
		}
		
		return sumatoria;

	}
	
	@GetMapping("/costos")
	public List<Float> getCostos(){
		List<Float> allCostos = new ArrayList<>();
		
		
		return allCostos;
	}
	/**
	 * 
	 * @param manufacturado
	 * @return True: Si todos los insumos del manufacturado tienen stock necesario
	 *         para fabricarlo
	 */
	/*
	 * public boolean verificarStock(ArticuloManufacturadoWrapper manufacturado) {
	 * return manufacturado.getRecetas().stream() .allMatch(r ->
	 * r.getCantidadInsumo() <= r.getInsumo().getStock().getActual()); }
	 */
}
