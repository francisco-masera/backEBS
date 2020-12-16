package ebs.back.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.Domicilio;
import ebs.back.service.DomicilioService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/domicilio")
public class DomicilioController extends BaseController<Domicilio, DomicilioService> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@GetMapping("domicilioXCliente/{idCliente}")
	public List<Domicilio> traeDomicilios(@PathVariable Long idCliente) {
		List<Domicilio> domicilios = new ArrayList<>();
		
	
			String query = "SELECT * from domicilio where idPersona = " + idCliente;
		    domicilios = jdbcTemplate.query(query, new RowMapper<Domicilio>() {
		    

				@Override
				public Domicilio mapRow(ResultSet rs, int rowNum) throws SQLException {
					Domicilio domicilio = new Domicilio();
					domicilio.setId(rs.getLong(1));
					domicilio.setBaja(rs.getBoolean(2));
					domicilio.setCalle(rs.getString(3));
					domicilio.setDepartamento(rs.getString(4));
					domicilio.setLocalidad(rs.getString(5));
					domicilio.setNumero(rs.getInt(6));
					domicilio.setPiso(rs.getInt(7));
					
					
					return domicilio;
				}
		    	
		    });
		
		return domicilios;
	}
}
