package ebs.back.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	private int getEstadoStock(@PathVariable Long id) throws SQLException {
		try {
			Stock stock = this.jdbcTemplate.queryForObject(
					"SELECT actual, maximo, minimo FROM stock s INNER JOIN insumo i ON s.idStock = i.idStock WHERE i.idInsumo = "
							+ id,
					new RowMapper<Stock>() {
						public Stock mapRow(ResultSet rs, int rowNumber) throws SQLException {
							Stock stock = new Stock();
							stock.setActual(rs.getLong("actual"));
							stock.setMaximo(rs.getLong("maximo"));
							stock.setMinimo(rs.getInt("minimo"));
							return stock;
						}
					});
			return this.establecerEstadoStock(stock.getActual(), stock.getMaximo(), stock.getMinimo());
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	private int establecerEstadoStock(long actual, long maximo, int minimo) {
		if (actual >= maximo || actual < minimo)
			return 1;
		else if (estadoCritico(actual, minimo, 10))
			return 2;
		return 3;
	}

	private boolean estadoCritico(long actual, int minimo, int porcentual) {
		return !(actual > (minimo + minimo * porcentual / 100));
	}

}
