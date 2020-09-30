package ebs.back.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.HistorialCompraAProveedores;
import ebs.back.entity.Insumo;
import ebs.back.service.HistorialCompraAProveedoresService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/compras")
public class HistorialCompraAProveedoresController
		extends BaseController<HistorialCompraAProveedores, HistorialCompraAProveedoresService> {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * @deprecated
	 * @param idInsumo
	 * @return El historial de compras a proveedores de un determinado insumo, según
	 *         la fecha más actual
	 */
	@GetMapping("/historial/{idInsumo}")
	public List<HistorialCompraAProveedores> getHistorialInsumoFechaActual(@PathVariable Long idInsumo) {

		List<HistorialCompraAProveedores> historial = this.jdbcTemplate
				.query("SELECT * FROM historialcompraaproveedores WHERE idInsumo = " + idInsumo
						+ " ORDER BY fechaCompra DESC LIMIT 1", new RowMapper<HistorialCompraAProveedores>() {
							@Override
							public HistorialCompraAProveedores mapRow(ResultSet rs, int rowNum) throws SQLException {
								HistorialCompraAProveedores historial = new HistorialCompraAProveedores();
								historial.setId(rs.getLong(1));
								historial.setCantidad(rs.getFloat(2));
								historial.setFechaCompra(rs.getTimestamp(3).toLocalDateTime());
								historial.setPrecioUnitario(rs.getFloat(4));
								return historial;
							}
						});
		return historial;
	}

	/**
	 * 
	 * @param idInsumo
	 * @return El historial completo de compras de un determinado insumo
	 */
	@GetMapping("/historialCompras/{idInsumo}")
	public List<HistorialCompraAProveedores> getHistorialInsumo(@PathVariable Long idInsumo) {

		List<HistorialCompraAProveedores> historial = this.jdbcTemplate.query(
				"SELECT * FROM historialcompraaproveedores WHERE idInsumo=" + idInsumo + " ORDER BY fechaCompra DESC",
				new RowMapper<HistorialCompraAProveedores>() {
					@Override
					public HistorialCompraAProveedores mapRow(ResultSet rs, int rowNum) throws SQLException {
						HistorialCompraAProveedores compra = new HistorialCompraAProveedores();
						compra.setId(rs.getLong(1));
						compra.setCantidad(rs.getFloat(2));
						compra.setFechaCompra(rs.getTimestamp(3).toLocalDateTime());
						compra.setPrecioUnitario(rs.getFloat(4));
						return compra;
					}
				});
		return historial;
	}

	private List<HistorialCompraAProveedores> traeUltimoCargado() {
		List<HistorialCompraAProveedores> ultimo = this.jdbcTemplate.query(
				"SELECT * FROM historialcompraaproveedores ORDER BY idCompra DESC LIMIT 1",
				new RowMapper<HistorialCompraAProveedores>() {
					@Override
					public HistorialCompraAProveedores mapRow(ResultSet rs, int rowNum) throws SQLException {
						HistorialCompraAProveedores compra = new HistorialCompraAProveedores();
						compra.setId(rs.getLong(1));
						compra.setCantidad(rs.getFloat(2));
						compra.setFechaCompra(rs.getTimestamp(3).toLocalDateTime());
						compra.setPrecioUnitario(rs.getFloat(4));
						Insumo insumo = new Insumo();
						insumo.setIdInsumo(rs.getLong(5));
						compra.setInsumo(insumo);
						return compra;
					}
				});
		return ultimo;
	}

	/**
	 * 
	 * @return El precio unitario actual de cada insumo
	 */
	@GetMapping("/preciosUnitariosActuales")
	public List<HistorialCompraAProveedores> getPreciosUnitariosActuales() {
		List<Long> idsCargados = this.jdbcTemplate.query(
				"SELECT h.idInsumo FROM historialcompraaproveedores h INNER JOIN insumo i "
						+ "ON h.idInsumo = i.idInsumo WHERE h.fechaCompra = (SELECT MAX(h1.fechaCompra) "
						+ "FROM historialcompraaproveedores h1 WHERE h1.idInsumo = i.idInsumo)",
				(rs, rowNum) -> rs.getLong(1));
		List<HistorialCompraAProveedores> compras = new ArrayList<>();

		for (Long id : idsCargados) {
			compras.add(this.jdbcTemplate.queryForObject(
					"SELECT h.precioUnitario, h.idInsumo FROM historialcompraaproveedores h WHERE h.idInsumo = ? ORDER BY h.fechaCompra DESC LIMIT 1",
					new Object[] { id }, (rs, rowNum) -> (new HistorialCompraAProveedores(null,
							rs.getFloat("precioUnitario"), 0.0F, null, new Insumo(rs.getLong("idInsumo"))))));
		}

		return compras;

	}

	private int actualizaStock(Long idInsumo, float compra) {
		Long idStock = this.jdbcTemplate.queryForObject("SELECT idStock FROM insumo WHERE idInsumo = " + idInsumo,
				Long.class);
		float actual = this.jdbcTemplate.queryForObject("SELECT actual FROM stock WHERE idStock = " + idStock,
				Long.class) + compra;
		return this.jdbcTemplate.update("UPDATE stock SET actual = " + actual + " WHERE idStock = " + idStock);
	}

	public ResponseEntity<?> save(@RequestBody HistorialCompraAProveedores entity) {

		try {
			ResponseEntity<?> response = ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
			List<HistorialCompraAProveedores> ultimo = traeUltimoCargado();
			actualizaStock(ultimo.get(0).getInsumo().getIdInsumo(), ultimo.get(0).getCantidad());
			return response;

		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"Error en la solicitud\": \"" + e.getMessage() + "\"}");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"Error inesperado\": \"" + e.getMessage() + "\"}");
		}
	}

	/**
	 * 
	 * @param id
	 * @return El precio unitario más actual de un insumo dado
	 */
	@GetMapping("/precioUnitario/{id}")
	public Float getPrecioUnitarioXiD(@PathVariable Long id) {
		try {
			return this.jdbcTemplate.queryForObject(
					"SELECT precioUnitario FROM historialcompraaproveedores WHERE fechaCompra = (SELECT MAX(fechaCompra)) AND idInsumo = "
							+ id + " ORDER BY fechaCompra DESC LIMIT 1",
					Float.class);
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0F;
		}
	}

}