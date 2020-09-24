package ebs.back.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ebs.back.entity.ArticuloManufacturado;
import ebs.back.entity.Insumo;
import ebs.back.entity.Receta;
import ebs.back.entity.Stock;
import ebs.back.entity.wrapper.ArticuloManufacturadoWrapper;
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
	 * @param id
	 * @return List<Receta>: Todas las recetas de un producto manufacturado
	 */
	@GetMapping("/recetasManufacturado/{id}")
	public List<Receta> getRecetasXManufacturado(@PathVariable Long id) {
		List<Receta> recetas = this.jdbcTemplate.query(
				"SELECT r.cantidadInsumo, i.idInsumo, i.denominacion, i.unidadMedida, s.actual "
						+ "FROM stock s INNER JOIN insumo i ON s.idStock = i.idStock INNER JOIN receta r ON i.idInsumo = r.idInsumo WHERE r.idManufacturado = "
						+ id,

				new RowMapper<Receta>() {
					@Override
					public Receta mapRow(ResultSet rs, int rowNum) throws SQLException {
						Receta receta = new Receta();
						receta.setCantidadInsumo(rs.getFloat("r.cantidadInsumo"));

						Insumo insumo = new Insumo();
						insumo.setIdInsumo(rs.getLong("i.idInsumo"));
						insumo.setDenominacion(rs.getString("i.denominacion"));
						insumo.setUnidadMedida(rs.getString("i.unidadMedida"));

						Stock stock = new Stock();
						stock.setActual(rs.getFloat("s.actual"));

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
	 * @return Float: Precio unitario más actual de un insumo
	 */
	private Float getPrecioUnitario(Long idInsumo) {
		return this.jdbcTemplate
				.queryForObject("SELECT precioUnitario FROM historialcompraaproveedores WHERE idInsumo = " + idInsumo
						+ " ORDER BY fechaCompra DESC LIMIT 1", Float.class);
	}

	/**
	 * 
	 * @param idsInsumosStr
	 * @param cantInsumo
	 * @return El costo de producción de un producto manufacturado
	 */
	@GetMapping("/costo")
	public Float getCosto(@RequestParam String idsInsumosStr, @RequestParam String cantidadInsumos) {
		List<String> idsAuxList = Arrays.asList(idsInsumosStr.split(","));
		List<String> cantInsumosAuxList = Arrays.asList(cantidadInsumos.split(","));
		List<Long> idsInsumos = idsAuxList.stream().map(Long::parseLong).collect(Collectors.toList());
		List<Float> cantInsumoList = cantInsumosAuxList.stream().map(Float::parseFloat).collect(Collectors.toList());

		List<Float> costosInsumo = idsInsumos.stream().map(id -> this.getPrecioUnitario(id))
				.collect(Collectors.toList());
		Float sumatoria = 0.0F;
		for (int i = 0; i < costosInsumo.size(); i++) {
			sumatoria += cantInsumoList.get(i) * costosInsumo.get(i);
		}

		return sumatoria;

	}

	/**
	 * 
	 * @param idsManufacturadosStr
	 * @return List<Float>: El costo de cada uno de los productos manufacturados
	 */
	@GetMapping("/costos")
	public List<Float> getCostos(@RequestParam String idsManufacturadosStr) {
		List<String> idsAuxList = Arrays.asList(idsManufacturadosStr.split(","));
		List<Long> idsManufacturados = idsAuxList.stream().map(Long::parseLong).collect(Collectors.toList());
		List<Float> costosManufacturados = new ArrayList<>();
		List<ArticuloManufacturadoWrapper> manufacturados = idsManufacturados.stream()
				.map(id -> this.convertirManufacturado(id)).collect(Collectors.toList());
		manufacturados.forEach(
				manufacturado -> manufacturado.setRecetas(this.getRecetasXManufacturado(manufacturado.getId())));
		for (ArticuloManufacturadoWrapper manufacturado : manufacturados) {
			if (!manufacturado.getRecetas().isEmpty())
				costosManufacturados.add(this.auxGetCostos(manufacturado.getRecetas()));
		}
		return costosManufacturados;
	}

	/**
	 * 
	 * @param recetasSugeridas
	 * @return Float: El costo de un producto manufacturado
	 */
	private Float auxGetCostos(List<Receta> recetas) {
		String idsInsumos = this.crearStrIdsInsumos(recetas);
		String cantidades = this.crearStrCantidades(recetas);
		return this.getCosto(idsInsumos, cantidades);

	}

	/**
	 * 
	 * @param recetas
	 * @return String: Cantidades de cada insumo para fabricar un producto
	 *         manufacturado, concatenadas con comas
	 */
	private String crearStrCantidades(List<Receta> recetas) {
		List<String> cantidades = new ArrayList<>();
		recetas.forEach(receta -> cantidades.add(String.valueOf(receta.getCantidadInsumo())));
		String s = String.join(",", cantidades);
		return s;
	}

	/**
	 * 
	 * @param recetas
	 * @return String: Id de cada insumo necesario para fabricar un producto
	 *         manufacturado, concatenados con comas
	 */
	private String crearStrIdsInsumos(List<Receta> recetas) {
		List<String> idsInsumos = new ArrayList<>();
		recetas.forEach(receta -> idsInsumos.add(receta.getInsumo().getIdInsumo().toString()));
		String s = String.join(",", idsInsumos);
		return s;
	}

	/**
	 * 
	 * @param idManufacturado
	 * @return {@link ArticuloManufacturadoWrapper}: Transforma el responseBody del
	 *         getOne de un producto manufacturado a su clase envolvente
	 */
	private ArticuloManufacturadoWrapper convertirManufacturado(Long idManufacturado) {
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		Object response = this.getOne(idManufacturado).getBody();

		String responseJson = "";
		try {
			responseJson = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		ArticuloManufacturadoWrapper manufacturadoWrapper = new ArticuloManufacturadoWrapper();
		try {
			manufacturadoWrapper = mapper.readValue(responseJson, ArticuloManufacturadoWrapper.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return manufacturadoWrapper;
	}

}
