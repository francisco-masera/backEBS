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
	 * @return Todas las recetas de un Manufacturado,
	 */
	@GetMapping("/recetasManufacturado/{id}")
	public List<Receta> getRecetasXManufacturado(@PathVariable Long id) {
		List<Receta> recetas = this.jdbcTemplate.query("SELECT r.cantidadInsumo, i.idInsumo "
				+ "FROM insumo i INNER JOIN receta r ON i.idInsumo = r.idInsumo WHERE r.idManufacturado = " + id,

				new RowMapper<Receta>() {
					@Override
					public Receta mapRow(ResultSet rs, int rowNum) throws SQLException {
						Receta receta = new Receta();
						receta.setCantidadInsumo(rs.getFloat("r.cantidadInsumo"));

						Insumo insumo = new Insumo();
						insumo.setIdInsumo(rs.getLong("i.idInsumo"));

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
				.queryForObject("SELECT precioUnitario FROM historialcompraaproveedores WHERE idInsumo = " + idInsumo
						+ " ORDER BY fechaCompra DESC LIMIT 1", Float.class);
	}

	/**
	 * 
	 * @param idsInsumosStr
	 * @param cantInsumo
	 * @return El costo de producción de un manufacturado
	 */
	@GetMapping("/costo")
	public Float getCosto(@RequestParam String idsInsumosStr, @RequestParam String cantInsumo) {
		List<String> idsAuxList = Arrays.asList(idsInsumosStr.split(","));
		List<String> cantInsumosAuxList = Arrays.asList(cantInsumo.split(","));
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

	private Float auxGetCostos(List<Receta> recetas) {
		String idsInsumos = this.crearStrIdsInsumos(recetas);
		String cantidades = this.crearStrCantidades(recetas);
		return this.getCosto(idsInsumos, cantidades);

	}

	private String crearStrCantidades(List<Receta> recetas) {
		List<String> cantidades = new ArrayList<>();
		recetas.forEach(receta -> cantidades.add(String.valueOf(receta.getCantidadInsumo())));
		String s = String.join(",", cantidades);
		return s;
	}

	private String crearStrIdsInsumos(List<Receta> recetas) {
		List<String> idsInsumos = new ArrayList<>();
		recetas.forEach(receta -> idsInsumos.add(receta.getInsumo().getIdInsumo().toString()));
		String s = String.join(",", idsInsumos);
		return s;
	}

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
