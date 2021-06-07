package ebs.back.controller;

import ebs.back.entity.Pedido;
import ebs.back.entity.wrapper.ItemPedidoPendiente;
import ebs.back.entity.wrapper.PedidoPendiente;
import ebs.back.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping(path = "buensabor/pedido")
public class PedidoController extends BaseController<Pedido, PedidoService> {

    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @GetMapping("/getCarritoPediente/{idCliente}")
    public PedidoPendiente getCarritoByCliente(@PathVariable Long idCliente) {
        try {

            List<ItemPedidoPendiente> items = jdbcTemplate.query("SELECT * FROM DetallePedido " +
                            " INNER JOIN InformacionArticuloVenta IAV on DetallePedido.idArticulo = IAV.idArticuloVenta" +
                            " LEFT JOIN Pedido P on P.idPedido = DetallePedido.idPedido where p.idCliente = ? AND p.estado = 'Pendiente'",
                    new Object[]{idCliente},
                    (rs, rowNum) -> new ItemPedidoPendiente(rs.getLong("idDetalle"), rs.getLong("idArticuloVenta"),
                            rs.getInt("cantidad"), rs.getFloat("precioVenta"), ""));


            for (ItemPedidoPendiente item : items) {
                String denominacion;
                if (existeByID("Select Count(idArticuloManufacturado) From ArticuloManufacturado Where idArticuloManufacturado = ?"
                        , item.getIdArticuloVenta())) {
                    denominacion = jdbcTemplate.queryForObject("Select denominacion FROM ArticuloManufacturado WHERE idArticuloManufacturado = ?",
                            new Object[]{item.getIdArticuloVenta()}, String.class);

                } else {
                    denominacion = jdbcTemplate.queryForObject("SELECT I.denominacion from informacionarticuloventa_insumo IA " +
                            "INNER JOIN Insumo I on IA.idInsumo = I.idInsumo" +
                            " WHERE IA.idInsumoVenta = ?", new Object[]{item.getIdArticuloVenta()}, String.class);
                }
                item.setDenominacion(denominacion);
            }


      /*      List<Float> precios = jdbcTemplate.query("Select IAV.precioVenta FROM InformacionArticuloVenta IAV" +
                            " JOIN DetallePedido DP on IAV.idArticuloVenta = DP.idArticulo" +
                            " INNER JOIN Pedido P on DP.idPedido = P.idPedido where p.idCliente = ?",
                    new Object[]{idCliente}, (rs, rowNum) -> rs.getFloat(1));*/

            //    Float total = precios.stream().reduce(0F, Float::sum);

            return jdbcTemplate.queryForObject("SELECT * FROM Pedido Where idCliente= ? AND estado = ?",
                    new Object[]{idCliente, "Pendiente"},
                    (rs, rowNum) -> new PedidoPendiente(
                            rs.getLong("idPedido"), rs.getLong("idCliente"),
                            rs.getBoolean("formaPago"), rs.getBoolean("tipoEntrega"),
                            rs.getString("estado"), rs.getLong("numero"), null, items));


        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    private Boolean existeByID(String sql, Long id) {
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class) == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private Boolean existePedido() {
        try {
            return jdbcTemplate.queryForObject("Select Count(idPedido) FROM Pedido Where estado = 'PENDIENTE'", Integer.class) == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private Boolean existeDetalle(Long idArticulo, Long idPedido) {
        try {
            return jdbcTemplate.queryForObject("Select Count(idDetalle) FROM DetallePedido Where idArticulo = ? AND idPedido=?",
                    new Object[]{idArticulo, idPedido}, Integer.class) > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    @PostMapping("/saveCarrito")
    public void pedidoPendiente(@RequestBody PedidoPendiente carrito) {
        Boolean hayPedidoPendiente;

        hayPedidoPendiente = existePedido();

        //LocalTime local = LocalTime.now();
        Time horaEstimada = Time.valueOf(LocalTime.of(0, 0, 0));

        //if (carrito.getHoraEstimada() != null) horaEstimada = Time.valueOf(carrito.getHoraEstimada());


        if (!hayPedidoPendiente) {
            try {


                jdbcTemplate.update("INSERT INTO Pedido (estado, hora, numero, tipoEntrega, idCliente, formaPago)  VALUES (?,?,?,?,?,?)"
                        , carrito.getEstado(), horaEstimada, carrito.getNumero(),
                        carrito.getTipoEntrega(), carrito.getIdCliente(), carrito.getFormaPago());


            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
        try {
            Long idPedido = jdbcTemplate.queryForObject("Select idPedido From Pedido Where estado = ? " +
                    "AND idCliente = ? Order BY idPedido Desc", new Object[]{"Pendiente", carrito.getIdCliente()}, Long.class);
            carrito.getItems().forEach(d -> {
                if (!existeDetalle(d.getIdArticuloVenta(), idPedido))
                    this.jdbcTemplate.update("INSERT INTO DetallePedido (cantidad, idArticulo, idPedido) VALUES(?,?,?)",
                            d.getCantidad(), d.getIdArticuloVenta(), idPedido);
                else
                    this.jdbcTemplate.update("UPDATE  DetallePedido SET cantidad = ? WHERE idPedido = ? AND idArticulo  = ?",
                            d.getCantidad(), idPedido, d.getIdArticuloVenta());

            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @DeleteMapping("/eliminarPedido/{idCliente}")
    public void eliminarPedido(@PathVariable Long idCliente) {
        try {
            Long idPedido = jdbcTemplate.queryForObject("SELECT idPedido FROM Pedido WHERE idCliente = ? AND " +
                    " estado = ?", new Object[]{idCliente, "PENDIENTE"}, Long.class);
            jdbcTemplate.update("DELETE FROM DetallePedido WHERE idPedido = ?", idPedido);
            jdbcTemplate.update("DELETE FROM Pedido WHERE idPedido = ?", idPedido);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @PutMapping("/confirmarPedido/{idPedido}")
    public boolean confirmarPedido(@PathVariable Long idPedido) {
        try {
            jdbcTemplate.update("Update Pedido SET estado = ?  WHERE idPedido = ?", "Confirmado", idPedido);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return true;
    }
}
