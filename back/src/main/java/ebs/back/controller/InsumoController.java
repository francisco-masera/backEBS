package ebs.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.Insumo;
import ebs.back.service.InsumoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/insumo")
public class InsumoController extends BaseController<Insumo, InsumoService> {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@GetMapping("/idStock/{id}")
	public Long getIdStock(@PathVariable Long id) {
		return this.jdbcTemplate.queryForObject("SELECT idStock FROM insumo WHERE idInsumo = " + id, Long.class);
	}

	@GetMapping("/insumosProduccion")
	public List<Insumo> getInsumosFabricacion() {
		return this.jdbcTemplate.query("SELECT * FROM Insumo Where esInsumo = 1",
				(rs, rowNum) -> new Insumo(rs.getLong(1), rs.getString(6), rs.getString(3), rs.getBoolean(4),
						rs.getBoolean(2), rs.getBoolean(5), null, null, null, null, null));
	}
}
