package ebs.back.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import ebs.back.entity.Insumo;
import ebs.back.entity.Receta;
import ebs.back.entity.Stock;
import ebs.back.entity.SugerenciaChef;
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
	 * @return Todas las recetas de una sugerencia,
	 */
	@GetMapping("/recetasManufacturado/{id}")
	public List<Receta> getRecetasXManufacturado(@PathVariable Long id) {
		List<Receta> recetas = this.jdbcTemplate.query(
				"SELECT r.idRecetaSugerida, r.cantidadInsumo, i.idInsumo, i.denominacion, i.unidadMedida, s.actual "
						+ "FROM stock s INNER JOIN insumo i ON s.idStock = i.idStock INNER JOIN recetasugerida r "
						+ "ON i.idInsumo = r.idInsumo WHERE r.idSugerencia = " + id,

				new RowMapper<Receta>() {
					@Override
					public Receta mapRow(ResultSet rs, int rowNum) throws SQLException {
						Receta receta = new Receta();
						receta.setId(rs.getLong("r.idRecetaSugerida"));
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
				.queryForObject("SELECT precioUnitario FROM historialcompraaproveedores WHERE idInsumo = " + idInsumo
						+ " ORDER BY fechaCompra DESC LIMIT 1", Float.class);
	}

	/**
	 * 
	 * @param idsInsumosStr
	 * @param cantInsumo
	 * @return El costo de producción de un manufacturado sugerido
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
	public List<Float> getCostos(@RequestParam String idsSugerencias) {
		
		return null;
	}
}
