package ebs.back.controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

import ebs.back.entity.HistorialCompraAProveedores;

import ebs.back.service.HistorialCompraAProveedoresService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/compras")
public class HistorialCompraAProveedoresController
		extends BaseController<HistorialCompraAProveedores, HistorialCompraAProveedoresService> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@GetMapping("/historial/{idInsumo}")
	public List<HistorialCompraAProveedores> getHistorialInsumo(@PathVariable Long idInsumo) {

		
		List<HistorialCompraAProveedores> historial = this.jdbcTemplate.query(
				"SELECT * FROM historialcompraaproveedores WHERE idInsumo = " + idInsumo
						+ " AND fechaCompra = (SELECT MAX(fechaCompra) FROM historialcompraaproveedores)",
				new RowMapper<HistorialCompraAProveedores>() {
					@Override
					public HistorialCompraAProveedores mapRow(ResultSet rs, int rowNum) throws SQLException {
						HistorialCompraAProveedores historial = new HistorialCompraAProveedores();
						historial.setId(rs.getLong(1));
						historial.setCantidad(rs.getFloat(2));
						Date date = rs.getDate(3);						
						LocalDateTime localDateTime1 = (date.toLocalDate()).atStartOfDay();
						
						historial.setFechaCompra(localDateTime1);
						historial.setPrecioUnitario(rs.getFloat(4));
						return historial;
					}
				});
		return historial;
	}
	
	@GetMapping("/historialCompras/{idInsumo}")
	public List<HistorialCompraAProveedores> getHistorialInsu(@PathVariable Long idInsumo) {

		
		List<HistorialCompraAProveedores> historial = this.jdbcTemplate.query(
				"SELECT * FROM historialcompraaproveedores WHERE idInsumo=" + idInsumo,
				new RowMapper<HistorialCompraAProveedores>() {
					@Override
					public HistorialCompraAProveedores mapRow(ResultSet rs, int rowNum) throws SQLException {
						HistorialCompraAProveedores compra = new HistorialCompraAProveedores();
						compra.setId(rs.getLong(1));
						compra.setCantidad(rs.getFloat(2));
						Date date = rs.getDate(3);						
						LocalDateTime localDateTime1 = (date.toLocalDate()).atStartOfDay();
						
						compra.setFechaCompra(localDateTime1);
						compra.setPrecioUnitario(rs.getFloat(4));
						return compra;
					}
				});
		return historial;
	}

}