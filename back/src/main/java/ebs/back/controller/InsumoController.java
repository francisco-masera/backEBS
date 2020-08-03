package ebs.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	private Long getIdStock(@PathVariable Long id) {
		return this.jdbcTemplate.queryForObject("SELECT idStock FROM insumo WHERE idInsumo = " + id, Long.class);
	}

}
