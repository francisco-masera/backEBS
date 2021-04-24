package ebs.back.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.Stock;
import ebs.back.service.StockService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/stock")
public class StockController extends BaseController<Stock, StockService> {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@GetMapping("/estadoStock/{id}")
	public int getEstadoStock(@PathVariable Long id) throws SQLException {
		try {
			Stock stock = this.jdbcTemplate.queryForObject(
					"SELECT actual, maximo, minimo FROM Stock s INNER JOIN Insumo i ON s.idStock = i.idStock WHERE i.idInsumo = "
							+ id,
					new RowMapper<Stock>() {
						public Stock mapRow(ResultSet rs, int rowNumber) throws SQLException {
							Stock stock = new Stock();
							stock.setActual(rs.getFloat("actual"));
							stock.setMaximo(rs.getFloat("maximo"));
							stock.setMinimo(rs.getFloat("minimo"));
							return stock;
						}
					});
			return this.establecerEstadoStock(stock.getActual(), stock.getMaximo(), stock.getMinimo());
		} catch (EmptyResultDataAccessException e) {
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	@GetMapping("/estadoStockManufacturado/{id}")
	public boolean getEstadoStockManufacturado(@PathVariable Long id) {
		List<Integer> estados = getIdInsumosById(id).stream().map(i -> {
			try {
				return this.getEstadoStock(i);
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}).collect(Collectors.toList());

		return estados.stream().allMatch(e -> e != 2);
	}

	private int establecerEstadoStock(float actual, float maximo, float minimo) {
		if (actual >= maximo)
			return 1;
		else if (actual < minimo)
			return 2;
		else if (estadoCritico(actual, minimo))
			return 3;
		return 4;
	}

	private boolean estadoCritico(float actual, float minimo) {
		return !(actual > (minimo + (minimo * 0.1)));
	}

	private List<Long> getIdInsumosById(Long id) {
		return jdbcTemplate.query("SELECT idInsumo FROM Receta Where idManufacturado = " + id,
				(rs, rowNum) -> rs.getLong(1));
	}
}
