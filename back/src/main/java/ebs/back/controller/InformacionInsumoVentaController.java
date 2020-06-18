package ebs.back.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
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

	@GetMapping("/insumo/{idInsumo}")
	public List<InformacionInsumoVenta> getInsumoVenta(@PathVariable Long idInsumo) {

		
		List<InformacionInsumoVenta>resultList =  this.jdbcTemplate.query(
				"SELECT * FROM informacionarticuloventa_insumo inner join insumo on informacionarticuloventa_insumo.idInsumo=insumo.idInsumo inner join informacionarticuloventa on informacionarticuloventa_insumo.idInsumoVenta=informacionarticuloventa.idArticuloVenta inner join stock on insumo.idStock = stock.idStock inner join rubroinsumo on insumo.idRubro = rubroinsumo.idRubroInsumo where insumo.idInsumo="+ idInsumo,
						
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
								stock.setActual(rs.getLong(16));
								stock.setMaximo(rs.getLong(17));
								stock.setMinimo(rs.getInt(18));
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
}
