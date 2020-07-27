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

import ebs.back.entity.Insumo;
import ebs.back.entity.Receta;
import ebs.back.entity.RecetaSugerida;
import ebs.back.entity.Stock;
import ebs.back.entity.SugerenciaChef;
import ebs.back.entity.wrapper.SugerenciaChefWrapper;
import ebs.back.service.SugerenciaChefService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/sugerencia")
public class SugerenciaChefController extends BaseController<SugerenciaChef, SugerenciaChefService> {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * 
	 * @param id
	 * @return List<RecetaSugerida>: Todas las recetas de una sugerencia
	 */
	@GetMapping("/recetasSugerencia/{id}")
	public List<RecetaSugerida> getRecetasXSugerencia(@PathVariable Long id) {
		List<RecetaSugerida> recetas = this.jdbcTemplate.query(
				"SELECT r.cantidadInsumo, i.idInsumo,i.denominacion, i.unidadMedida, s.actual "
				+ "From stock s inner join insumo i on s.idStock = i.idStock inner join recetasugerida r "
				+ "ON i.idInsumo = r.idInsumo WHERE r.idRecetaSugerida="+ id,

				new RowMapper<RecetaSugerida>() {
					@Override
					public RecetaSugerida mapRow(ResultSet rs, int rowNum) throws SQLException {
						RecetaSugerida receta = new RecetaSugerida();
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
	 * @return Float: El precio unitario más actual de un insumo
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
	 * @return Float: El costo de producción de un manufacturado sugerido
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
	 * @param idsSugerenciasStr
	 * @return List<Float>: El costo de cada una de las sugerencias
	 */
	@GetMapping("/costos")
	public List<Float> getCostos(@RequestParam String idsSugerenciasStr) {
		List<String> idsAuxList = Arrays.asList(idsSugerenciasStr.split(","));
		List<Long> idsSugerencias = idsAuxList.stream().map(Long::parseLong).collect(Collectors.toList());
		List<Float> costosSugerencias = new ArrayList<>();
		List<SugerenciaChefWrapper> sugerencias = idsSugerencias.stream().map(id -> this.convertirSugerencia(id))
				.collect(Collectors.toList());
		sugerencias.forEach(
				manufacturado -> manufacturado.setRecetasSugeridas(this.getRecetasXSugerencia(manufacturado.getId())));
		for (SugerenciaChefWrapper sugerencia : sugerencias) {
			if (!sugerencia.getRecetasSugeridas().isEmpty())
				costosSugerencias.add(this.auxGetCostos(sugerencia.getRecetasSugeridas()));
		}
		return costosSugerencias;
	}

	/**
	 * 
	 * @param recetasSugeridas
	 * @return Float: El costo de una sugerencia
	 */
	private Float auxGetCostos(List<RecetaSugerida> recetasSugeridas) {
		String idsInsumos = this.crearStrIdsInsumos(recetasSugeridas);
		String cantidades = this.crearStrCantidades(recetasSugeridas);
		return this.getCosto(idsInsumos, cantidades);

	}

	/**
	 * 
	 * @param recetasSugeridas
	 * @return String: Cantidades de cada insumo para fabricar un producto sugerido,
	 *         concatenadas con comas
	 */
	private String crearStrCantidades(List<RecetaSugerida> recetasSugeridas) {
		List<String> cantidades = new ArrayList<>();
		recetasSugeridas.forEach(receta -> cantidades.add(String.valueOf(receta.getCantidadInsumo())));
		return String.join(",", cantidades);
	}

	/**
	 * 
	 * @param recetasSugeridas
	 * @return String: Id de cada insumo necesario para fabricar un producto
	 *         sugerido, concatenados con comas
	 */
	private String crearStrIdsInsumos(List<RecetaSugerida> recetasSugeridas) {
		List<String> idsInsumos = new ArrayList<>();
		recetasSugeridas.forEach(receta -> idsInsumos.add(receta.getInsumo().getIdInsumo().toString()));
		return String.join(",", idsInsumos);

	}

	/**
	 * 
	 * @param idSugerencia
	 * @return {@link SugerenciaChefWrapper}: Transforma el responseBody del getOne
	 *         de un producto sugerido a su clase envolvente
	 */
	private SugerenciaChefWrapper convertirSugerencia(Long idSugerencia) {
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		Object response = this.getOne(idSugerencia).getBody();

		String responseJson = "";
		try {
			responseJson = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		SugerenciaChefWrapper sugerenciaWrapper = new SugerenciaChefWrapper();
		try {
			sugerenciaWrapper = mapper.readValue(responseJson, SugerenciaChefWrapper.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return sugerenciaWrapper;
	}
}
