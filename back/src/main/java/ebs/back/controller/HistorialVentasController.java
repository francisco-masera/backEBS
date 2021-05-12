package ebs.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.HistorialVentas;
import ebs.back.service.HistorialVentasService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/ventas")
public class HistorialVentasController extends BaseController<HistorialVentas, HistorialVentasService> {

	@Autowired
	private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@GetMapping("/costos")
	public List<Float> getCostos() {
		return this.jdbcTemplate.queryForList("SELECT costo from HistorialVentas", Float.class);
	}
}
