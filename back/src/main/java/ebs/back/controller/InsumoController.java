package ebs.back.controller;

import ebs.back.entity.Insumo;
import ebs.back.entity.RubroInsumo;
import ebs.back.entity.Stock;
import ebs.back.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping(path = "buensabor/insumo")
public class InsumoController extends BaseController<Insumo, InsumoService> {

    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @GetMapping("/idStock/{id}")
    public Long getIdStock(@PathVariable Long id) {
        return this.jdbcTemplate.queryForObject("SELECT idStock FROM Insumo WHERE idInsumo = " + id, Long.class);
    }

    @GetMapping("/insumosProduccion")
    public List<Insumo> getInsumosFabricacion() {
        return this.jdbcTemplate.query(
                "SELECT * FROM Insumo i NATURAL LEFT JOIN HistorialCompraAProveedores hc " +
                        "WHERE i.esInsumo = 1 AND i.Baja = 0 AND hc.IdInsumo IS NOT NULL GROUP BY hc.idInsumo",
                (rs, rowNum) -> new Insumo(rs.getLong(1), rs.getString(6), rs.getString(3), rs.getBoolean(4),
                        rs.getBoolean(2), rs.getBoolean(5), null, null, null, null, null));
    }

    @GetMapping("/getDetalleByID/{id}")
    public Insumo getDetalleByID(@PathVariable long id) {
        try {
            return this.jdbcTemplate.queryForObject("SELECT i.idInsumo, i.baja, i.denominacion, i.esExtra, i.esInsumo, i.unidadMedida, ii.idInsumoVenta," +
                            "s.idStock, s.actual, s.maximo, s.minimo, r.idRubroInsumo, r.denominacion " +
                            "FROM Insumo i LEFT JOIN informacionarticuloventa_insumo ii ON i.idInsumo = ii.idInsumo " +
                            "LEFT JOIN Stock s ON i.idStock = s.idStock " +
                            "INNER JOIN RubroInsumo r on i.idRubro = r.idRubroInsumo " +
                            "WHERE i.idInsumo = ?", new Object[]{id},
                    (rs, rowNum) -> new Insumo(rs.getLong(1), rs.getString(6), rs.getString(3), rs.getBoolean(4),
                            rs.getBoolean(2), rs.getBoolean(5), new Stock(
                            rs.getLong(8), rs.getFloat(9), rs.getFloat(11), rs.getFloat(10), null
                    ), new RubroInsumo(rs.getLong(12), rs.getString(13), null),
                            null, null, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @GetMapping("/getAdicionales")
    public List<Insumo> getAdicionales() {
        try {
            return jdbcTemplate.query("SELECT idInsumo, denominacion From Insumo WHERE esExtra = ?", new Object[]{true}, (rs, rowNum) -> new Insumo(
                    rs.getLong("idInsumo"), null, rs.getString("denominacion")));
        } catch (Exception ex) {
            throw ex;
        }
    }
}
