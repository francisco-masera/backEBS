package ebs.back.controller;

import ebs.back.entity.HistorialCompraAProveedores;
import ebs.back.entity.Insumo;
import ebs.back.service.HistorialCompraAProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/compras")
public class HistorialCompraAProveedoresController
		extends BaseController<HistorialCompraAProveedores, HistorialCompraAProveedoresService> {

	@Autowired
	private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * @deprecated
	 * @param idInsumo
	 * @return El historial de compras a proveedores de un determinado insumo, según
	 *         la fecha más actual
	 */
	@GetMapping("/historial/{idInsumo}")
	public List<HistorialCompraAProveedores> getHistorialInsumoFechaActual(@PathVariable Long idInsumo) {

		return this.jdbcTemplate
				.query("SELECT * FROM HistorialCompraAProveedores WHERE idInsumo = " + idInsumo
						+ " ORDER BY fechaCompra DESC LIMIT 1", (rs, rowNum) -> {
							HistorialCompraAProveedores historial = new HistorialCompraAProveedores();
							historial.setId(rs.getLong(1));
							historial.setCantidad(rs.getFloat(2));
							historial.setFechaCompra(rs.getTimestamp(3).toLocalDateTime());
							historial.setPrecioUnitario(rs.getFloat(4));
							return historial;
						});
	}

	/**
	 * 
	 * @param idInsumo
	 * @return El historial completo de compras de un determinado insumo
	 */
	@GetMapping("/historialCompras/{idInsumo}")
	public List<HistorialCompraAProveedores> getHistorialInsumo(@PathVariable Long idInsumo) {

		return this.jdbcTemplate.query(
				"SELECT * FROM HistorialCompraAProveedores WHERE idInsumo=" + idInsumo + " ORDER BY fechaCompra DESC",
				(rs, rowNum) -> {
					HistorialCompraAProveedores compra = new HistorialCompraAProveedores();
					compra.setId(rs.getLong(1));
					compra.setCantidad(rs.getFloat(2));
					compra.setFechaCompra(rs.getTimestamp(3).toLocalDateTime());
					compra.setPrecioUnitario(rs.getFloat(4));
					return compra;
				});
	}

	private List<HistorialCompraAProveedores> traeUltimoCargado() {
		return this.jdbcTemplate.query(
				"SELECT * FROM HistorialCompraAProveedores ORDER BY idCompra DESC LIMIT 1",
				(rs, rowNum) -> {
					HistorialCompraAProveedores compra = new HistorialCompraAProveedores();
					compra.setId(rs.getLong(1));
					compra.setCantidad(rs.getFloat(2));
					compra.setFechaCompra(rs.getTimestamp(3).toLocalDateTime());
					compra.setPrecioUnitario(rs.getFloat(4));
					Insumo insumo = new Insumo();
					insumo.setIdInsumo(rs.getLong(5));
					compra.setInsumo(insumo);
					return compra;
				});
	}

	/**
	 * 
	 * @return El precio unitario actual de cada insumo
	 */
	@GetMapping("/preciosUnitariosActuales")
	public List<HistorialCompraAProveedores> getPreciosUnitariosActuales() {
		List<Long> idsCargados = this.jdbcTemplate.query(
				"SELECT h.idInsumo FROM HistorialCompraAProveedores h INNER JOIN Insumo i "
						+ "ON h.idInsumo = i.idInsumo WHERE h.fechaCompra = (SELECT MAX(h1.fechaCompra) "
						+ "FROM HistorialCompraAProveedores h1 WHERE h1.idInsumo = i.idInsumo)",
				(rs, rowNum) -> rs.getLong(1));
		List<HistorialCompraAProveedores> compras = new ArrayList<>();

		for (Long id : idsCargados) {
			compras.add(this.jdbcTemplate.queryForObject(
					"SELECT h.precioUnitario, h.idInsumo FROM HistorialCompraAProveedores h WHERE h.idInsumo = ? ORDER BY h.fechaCompra DESC LIMIT 1",
					new Object[] { id }, (rs, rowNum) -> (new HistorialCompraAProveedores(null,
							rs.getFloat("precioUnitario"), 0.0F, null, new Insumo(rs.getLong("idInsumo"))))));
		}

		return compras;

	}

	private int actualizaStock(Long idInsumo, float compra) {
		Long idStock = this.jdbcTemplate.queryForObject("SELECT idStock FROM Insumo WHERE idInsumo = " + idInsumo,
				Long.class);
		float actual = this.jdbcTemplate.queryForObject("SELECT actual FROM Stock WHERE idStock = " + idStock,
				Long.class) + compra;
		return this.jdbcTemplate.update("UPDATE Stock SET actual = " + actual + " WHERE idStock = " + idStock);
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
					"SELECT precioUnitario FROM HistorialCompraAProveedores WHERE fechaCompra = (SELECT MAX(fechaCompra)) AND idInsumo = "
							+ id + " ORDER BY fechaCompra DESC LIMIT 1",
					Float.class);
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0F;
		}
	}

}