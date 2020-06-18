package ebs.back.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	 * @param idManufacturado
	 * @return True: Si un Manufacturado puede elaborarse, teniendo en cuenta si hay
	 * stock suficiente de sus insumos
	 */
	@GetMapping("/stockManufacturado/{id}")
	public boolean hayStockManufacturado(@PathVariable Long id) {

		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		Object response = this.getOne(id).getBody();

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

		manufacturadoWrapper.setRecetas(this.getRecetasXManufacturado(id));
		return this.verificarStock(manufacturadoWrapper);
	}

	/**
	 * 
	 * @param idManufacturado
	 * @return Todas las recetas de un Manufacturado, con sus Insumos
	 */
	public List<Receta> getRecetasXManufacturado(Long idManufacturado) {
		List<Receta> resultList = this.jdbcTemplate.query(
				"SELECT r.idReceta, r.cantidadInsumo, i.idInsumo, s.actual "
						+ "FROM stock s INNER JOIN insumo i ON s.idStock = i.idStock INNER JOIN receta r "
						+ "ON i.idInsumo = r.idInsumo WHERE r.idManufacturado = " + idManufacturado,

				new RowMapper<Receta>() {
					@Override
					public Receta mapRow(ResultSet rs, int rowNum) throws SQLException {
						Receta receta = new Receta();
						receta.setId(rs.getLong("r.idReceta"));
						receta.setCantidadInsumo(rs.getFloat("r.cantidadInsumo"));

						Insumo insumo = new Insumo();
						insumo.setIdInsumo(rs.getLong("i.idInsumo"));

						Stock stock = new Stock();
						stock.setActual(rs.getLong("actual"));
						insumo.setStock(stock);
						receta.setInsumo(insumo);
						return receta;
					}
				});

		return resultList;
	}

	/**
	 * 
	 * @param manufacturado
	 * @return True: Si Todos los insumos del manufacturado tienen stock necesario
	 *         para fabricarlo
	 */
	public boolean verificarStock(ArticuloManufacturadoWrapper manufacturado) {
		return manufacturado.getRecetas().stream()
				.allMatch(r -> r.getCantidadInsumo() <= r.getInsumo().getStock().getActual());
	}

}
