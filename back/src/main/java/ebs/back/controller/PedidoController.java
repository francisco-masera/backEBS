package ebs.back.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.Cliente;
import ebs.back.entity.Domicilio;
import ebs.back.entity.Pedido;
import ebs.back.entity.Persona;
import ebs.back.entity.Receta;
import ebs.back.service.PedidoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/pedido")
public class PedidoController extends BaseController<Pedido, PedidoService> {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();


}
