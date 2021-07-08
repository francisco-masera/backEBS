package ebs.back.controller;

import ebs.back.entity.Factura;
import ebs.back.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping(path = "buensabor/factura")
public class FacturaController extends BaseController<Factura, FacturaService> {

    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @GetMapping("/ingresos")
    public Double ingresos(@RequestParam int yMax, @RequestParam int mMax, @RequestParam int dMax,
                           @RequestParam int yMin, @RequestParam int mMin, @RequestParam int dMin) throws Exception {
        try {
            java.sql.Timestamp maxFecha = Timestamp.valueOf(LocalDateTime.of(yMax, mMax, dMax, 0, 0, 0));
            java.sql.Timestamp minFecha = Timestamp.valueOf(LocalDateTime.of(yMin, mMin, dMin, 0, 0, 0));
            if (minFecha.after(maxFecha)) {
                throw new Exception("La fecha mínima no puede ser mayor a la máxima.");
            }
            // List<Double> res = new ArrayList<>();
            return jdbcTemplate.queryForObject("SELECT SUM(f.total) FROM Factura f" +
                    " WHERE F.fechaHora < ? && f.fechaHora > ?", Double.class, maxFecha, minFecha);

           /* Double ingresosManufacturados = jdbcTemplate.queryForObject("SELECT SUM(f.total) FROM Factura f" +
                    " INNER JOIN Pedido P on f.idPedido = P.idPedido" +
                    " INNER JOIN DetallePedido DP on P.idPedido = DP.idPedido" +
                    " INNER JOIN informacionarticuloventa i on DP.idArticulo = i.idArticuloVenta" +
                    " INNER JOIN articulomanufacturado a on i.idArticuloVenta = a.idArticuloManufacturado" +
                    " WHERE F.fechaHora < ? && f.fechaHora > ?", Double.class, maxFecha, minFecha);
            res.add(ingresosManufacturados);*/

         /*   Double ingresosInsumos = jdbcTemplate.queryForObject("SELECT SUM(f.total) FROM Factura f" +
                    " INNER JOIN Pedido P on f.idPedido = P.idPedido" +
                    " INNER JOIN DetallePedido DP on P.idPedido = DP.idPedido" +
                    " INNER JOIN informacionarticuloventa i on DP.idArticulo = i.idArticuloVenta" +
                    " INNER JOIN informacionarticuloventa_insumo ii on i.idArticuloVenta = ii.idInsumoVenta" +
                    " WHERE F.fechaHora < ? && f.fechaHora > ?", Double.class, maxFecha, minFecha);

            res.add(ingresosInsumos);*/

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
