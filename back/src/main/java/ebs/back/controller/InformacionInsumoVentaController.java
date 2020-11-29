package ebs.back.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import ebs.back.entity.InformacionInsumoVenta;
import ebs.back.entity.Insumo;
import ebs.back.entity.RubroInsumo;
import ebs.back.entity.Stock;
import ebs.back.service.InformacionInsumoVentaService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/insumoVenta")
public class InformacionInsumoVentaController
		extends BaseController<InformacionInsumoVenta, InformacionInsumoVentaService> {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * 
	 * @param idInsumo
	 * @return Todos los insumos de venta directa
	 */
	@GetMapping("/insumo/{idInsumo}")
	public List<InformacionInsumoVenta> getInsumoVenta(@PathVariable Long idInsumo) {

		List<InformacionInsumoVenta> resultList = this.jdbcTemplate.query(
				"SELECT * FROM informacionarticuloventa_insumo "
						+ "INNER JOIN insumo ON informacionarticuloventa_insumo.idInsumo = insumo.idInsumo "
						+ "INNER JOIN informacionarticuloventa ON informacionarticuloventa_insumo.idInsumoVenta = informacionarticuloventa.idArticuloVenta "
						+ "INNER JOIN stock ON insumo.idStock = stock.idStock "
						+ "INNER JOIN rubroinsumo ON insumo.idRubro = rubroinsumo.idRubroInsumo "
						+ "WHERE insumo.idInsumo=" + idInsumo,

				new RowMapper<InformacionInsumoVenta>() {

					@Override
					public InformacionInsumoVenta mapRow(ResultSet rs, int rowNum) throws SQLException {
						InformacionInsumoVenta insumoVenta = new InformacionInsumoVenta();
						insumoVenta.setId(rs.getLong(1));
						insumoVenta.setDescripcion(rs.getString(12));
						insumoVenta.setImagen(rs.getString(13));
						insumoVenta.setPrecioVenta(rs.getFloat(14));

						Insumo insu = new Insumo();
						insu.setIdInsumo(rs.getLong(2));
						insu.setBaja(rs.getBoolean(4));
						insu.setDenominacion(rs.getString(5));
						insu.setEsExtra(rs.getBoolean(6));
						insu.setEsInsumo(rs.getBoolean(7));
						insu.setUnidadMedida(rs.getString(8));

						Stock stock = new Stock();
						stock.setId(rs.getLong(10));
						stock.setActual(rs.getFloat(16));
						stock.setMaximo(rs.getFloat(17));
						stock.setMinimo(rs.getFloat(18));
						insu.setStock(stock);

						RubroInsumo rubro = new RubroInsumo();
						rubro.setId(rs.getLong(9));
						rubro.setDenominacion(rs.getString(20));
						insu.setRubroInsumo(rubro);
						insumoVenta.setInsumo(insu);
						return insumoVenta;
					}
				});
		return resultList;
	}

	@GetMapping("/conStock")
	public List<InformacionInsumoVenta> getInsumosConStock() {
		List<Insumo> insumos = this.jdbcTemplate.query(
				"SELECT i.idInsumo, i.unidadMedida, i.denominacion, i.baja, "
						+ "s.idStock, s.actual, r.idRubroInsumo, r.denominacion "
						+ "FROM Insumo i INNER JOIN Stock s ON i.idStock = s.idStock "
						+ "INNER JOIN rubroinsumo r ON r.idRubroInsumo = i.idRubro "
						+ "WHERE i.esInsumo = 0 AND i.Baja = 0 AND s.actual > 0 ORDER BY i.denominacion",
				(rs, rowNum) -> new Insumo(rs.getLong(1), rs.getString(2), rs.getString(3), false, false,
						rs.getBoolean(4), new Stock(rs.getLong(5), rs.getFloat(6), 0.0F, 0.0F, null),
						new RubroInsumo(rs.getLong(7), rs.getString(8), null), null, null, null));

		List<InformacionInsumoVenta> insumosVenta = new ArrayList<>();
		for (Insumo insumo : insumos) {

			InformacionInsumoVenta informacion = this.jdbcTemplate.queryForObject(
					"SELECT ia.idArticuloVenta, ia.imagen, ia.descripcion, ia.precioVenta  "
							+ "FROM Insumo i INNER JOIN informacionarticuloventa_insumo ii ON i.idInsumo = ii.idInsumo "
							+ "INNER JOIN informacionarticuloventa ia ON ia.idArticuloVenta = ii.idInsumoVenta "
							+ "WHERE i.esInsumo = 0 AND i.Baja = 0 AND i.idInsumo = ?",
					new Object[] { insumo.getIdInsumo() }, (rs, rowNum) -> new InformacionInsumoVenta(rs.getLong(1),
							rs.getString(3), rs.getFloat(4), rs.getString(2), null, null, insumo));
			informacion.setInsumo(insumo);
			insumosVenta.add(informacion);
		}
		return insumosVenta;
	}
}
