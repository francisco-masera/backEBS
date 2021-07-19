package ebs.back.controller;

import ebs.back.entity.Cliente;
import ebs.back.entity.Factura;
import ebs.back.entity.Pedido;
import ebs.back.entity.wrapper.*;
import ebs.back.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

            List<ItemPedidoPendiente> items = jdbcTemplate.query("SELECT * FROM DetallePedido "
                            + " INNER JOIN InformacionArticuloVenta IAV on DetallePedido.idArticulo = IAV.idArticuloVenta"
                            + " LEFT JOIN Pedido P on P.idPedido = DetallePedido.idPedido where p.idCliente = ? AND p.estado = 'En Espera'",
                    new Object[]{idCliente}, (rs, rowNum) -> new ItemPedidoPendiente(rs.getLong("idDetalle"), rs.getLong("idArticuloVenta"), rs.getInt("cantidad"), rs.getFloat("precioVenta"), ""));

            for (ItemPedidoPendiente item : items) {
                String denominacion;
                if (existeByID(
                        "Select Count(idArticuloManufacturado) From ArticuloManufacturado Where idArticuloManufacturado = ?",
                        item.getIdArticuloVenta())) {
                    denominacion = jdbcTemplate.queryForObject(
                            "Select denominacion FROM ArticuloManufacturado WHERE idArticuloManufacturado = ?",
                            new Object[]{item.getIdArticuloVenta()}, String.class);

                } else {
                    denominacion = jdbcTemplate.queryForObject(
                            "SELECT I.denominacion from informacionarticuloventa_insumo IA "
                                    + "INNER JOIN Insumo I on IA.idInsumo = I.idInsumo" + " WHERE IA.idInsumoVenta = ?",
                            new Object[]{item.getIdArticuloVenta()}, String.class);
                }
                item.setDenominacion(denominacion);
            }

            /*
             * List<Float> precios =
             * jdbcTemplate.query("Select IAV.precioVenta FROM InformacionArticuloVenta IAV"
             * + " JOIN DetallePedido DP on IAV.idArticuloVenta = DP.idArticulo" +
             * " INNER JOIN Pedido P on DP.idPedido = P.idPedido where p.idCliente = ?", new
             * Object[]{idCliente}, (rs, rowNum) -> rs.getFloat(1));
             */

            // Float total = precios.stream().reduce(0F, Float::sum);

            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Pedido Where idCliente= ? AND estado = ?", new Object[]{idCliente, "En Espera"}, (
                            rs, rowNum
                    ) -> new PedidoPendiente(rs.getLong("idPedido"), rs.getLong("idCliente"),
                            rs.getBoolean("formaPago"), rs.getBoolean("tipoEntrega"), rs.getString("estado"),
                            rs.getLong("numero"), null, items));

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

    private Boolean existePedido(Long idCliente) {
        try {
            return jdbcTemplate.queryForObject("Select Count(idPedido) FROM Pedido Where estado = 'En Espera' AND idCliente = ?",
                    Integer.class, idCliente) == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private Boolean existeDetalle(Long idArticulo, Long idPedido) {
        try {
            return jdbcTemplate.queryForObject(
                    "Select Count(idDetalle) FROM DetallePedido Where idArticulo = ? AND idPedido = ?",
                    new Object[]{idArticulo, idPedido}, Integer.class) > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private Long getUltimoNumeroPedido() {
        try {
            return jdbcTemplate.queryForObject("SELECT numero FROM Pedido ORDER BY idPedido DESC LIMIT 1", Long.class);
        } catch (Exception ex) {
            return null;
        }
    }

    @PostMapping("/saveCarrito")
    public void guardarCarrito(@RequestBody PedidoPendiente carrito) {
        Boolean hayPedidoPendiente = existePedido(carrito.getIdCliente());

        // LocalTime local = LocalTime.now();
        Time horaEstimada = Time.valueOf(LocalTime.of(0, 0, 0));

        // if (carrito.getHoraEstimada() != null) horaEstimada =
        // Time.valueOf(carrito.getHoraEstimada());


        if (!hayPedidoPendiente) {

            try {
                Long numeroPedido = getUltimoNumeroPedido();
                if (numeroPedido == null)
                    numeroPedido = 1L;
                else
                    numeroPedido += 1;
                jdbcTemplate.update(
                        "INSERT INTO Pedido (estado, hora, numero, tipoEntrega, idCliente, formaPago)  VALUES (?,?,?,?,?,?)",
                        carrito.getEstado(), horaEstimada, numeroPedido, carrito.getTipoEntrega(),
                        carrito.getIdCliente(), carrito.getFormaPago());

            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
        try {

            Long idPedido = jdbcTemplate.queryForObject(
                    "Select idPedido From Pedido Where estado = ? " + "AND idCliente = ? Order BY idPedido Desc",
                    new Object[]{"En Espera", carrito.getIdCliente()}, Long.class);
            carrito.getItems().forEach(d -> {
                if (!existeDetalle(d.getIdArticuloVenta(), idPedido))
                    this.jdbcTemplate.update("INSERT INTO DetallePedido (cantidad, idArticulo, idPedido) VALUES(?,?,?)",
                            d.getCantidad(), d.getIdArticuloVenta(), idPedido);
                else
                    this.jdbcTemplate.update(
                            "UPDATE  DetallePedido SET cantidad = ? WHERE idPedido = ? AND idArticulo  = ?",
                            d.getCantidad(), idPedido, d.getIdArticuloVenta());

            });
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @DeleteMapping("/eliminarPedido/{idCliente}")
    public void eliminarPedido(@PathVariable Long idCliente) {
        try {
            Long idPedido = jdbcTemplate.queryForObject(
                    "SELECT idPedido FROM Pedido WHERE idCliente = ? AND " + " estado = ?",
                    new Object[]{idCliente, "En Espera"}, Long.class);
            jdbcTemplate.update("DELETE FROM DetallePedido WHERE idPedido = ?", idPedido);
            jdbcTemplate.update("DELETE FROM Pedido WHERE idPedido = ?", idPedido);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @PutMapping("/pedidoEntregado/{id}/{estado}")
    private void cambiarEstado(@PathVariable String id, @PathVariable String estado) {

        try {
            this.jdbcTemplate
                    .update("UPDATE Pedido set estado = " + "'" + estado + "'" + "where idPedido = " + "'" + id + "'");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @PutMapping("/confirmarPedidoCliente")
    public boolean confirmarPedidoCliente(@RequestBody PedidoPendiente pedido) {
        try {
            LocalTime time = LocalTime.now();
            time = time.plusMinutes(pedido.getTiempoEstimado());
            //Time hora = Time.valueOf(time);
            jdbcTemplate.update(
                    "Update Pedido SET estado = ?, formaPago = ?, tipoEntrega = ?, hora = ? WHERE idPedido = ?",
                    "Pendiente", pedido.getFormaPago(), pedido.getTipoEntrega(), time, pedido.getIdPedido());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return true;
    }

    @GetMapping("/pedidosPorCliente")
    public List<Pedido> pedidosPorCliente(@RequestParam int yMax, @RequestParam int mMax, @RequestParam int dMax,
                                          @RequestParam int yMin, @RequestParam int mMin, @RequestParam int dMin) throws Exception {
        try {
            Timestamp maxFecha = Timestamp.valueOf(LocalDateTime.of(yMax, mMax, dMax, 0, 0, 0));
            Timestamp minFecha = Timestamp.valueOf(LocalDateTime.of(yMin, mMin, dMin, 0, 0, 0));
            if (minFecha.after(maxFecha)) {
                throw new Exception("La fecha mínima no puede ser mayor a la máxima.");
            }
            List<Pedido> pedidos = jdbcTemplate.query("SELECT p.idPedido, p.tipoEntrega, F.numero, p2.idPersona, p2.email, " +
                    "CONCAT(p2.nombre, ' ', p2.apellido) as nombreCompleto, " +
                    "F.fechaHora as fechaFacturacion, F.porcentajeDescuento, F.total, F.formaPago FROM Pedido p" +
                    " INNER JOIN cliente c on p.idCliente = c.idCliente" +
                    " INNER JOIN persona p2 on c.idCliente = p2.idPersona" +
                    " INNER JOIN Factura F on p.idPedido = F.idPedido" +
                    " WHERE F.fechaHora BETWEEN ? AND ?" +
                    " GROUP BY p2.idPersona, nombreCompleto ORDER BY nombreCompleto", (rs, rowNum) -> new Pedido(
                    rs.getLong("idPedido"), 0L, "", null, rs.getBoolean("tipoEntrega"),
                    new Factura(0L, rs.getTimestamp("fechaFacturacion").toLocalDateTime(), rs.getLong("numero"),
                            rs.getFloat("porcentajeDescuento") * rs.getFloat("total"), rs.getFloat("total"),
                            rs.getBoolean("formaPago"), null), new Cliente(rs.getString("nombreCompleto"),
                    rs.getString("email")), null
            ), minFecha, maxFecha);
            return pedidos;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @GetMapping("/pedidos")
    public List<PedidoWrapper> pedidos() {
        try {

            List<PedidoWrapper> pedidos = jdbcTemplate.query("SELECT P.*, PE.nombre, PE.apellido, PE.telefono " +
                    "FROM Pedido P INNER JOIN cliente c on P.idCliente = c.idCliente INNER JOIN persona PE on c.idCliente = PE.idPersona" +
                    " ", (rs, rowNum) -> new PedidoWrapper(
                    rs.getLong("idPedido"), rs.getLong("numero"), rs.getString("estado"),
                    rs.getTime("hora").toLocalTime(), rs.getBoolean("formaPago"),
                    rs.getBoolean("tipoEntrega"), null, new Cliente(
                    rs.getLong("idCliente"), rs.getString("nombre"),
                    rs.getString("apellido"), rs.getString("telefono")
            ), null, 0F
            ));

            pedidos.forEach(p -> p.setDetalles(jdbcTemplate.query("SELECT " +
                    "DetallePedido.*, a.denominacion as denominacion, " +
                    "I2.denominacion AS denominacion, I.precioVenta FROM DetallePedido " +
                    "Natural JOIN informacionarticuloventa I " +// ON DetallePedido.idArticulo = I.idArticuloVenta " +
                    "LEFT JOIN articulomanufacturado a ON I.idArticuloVenta = a.idArticuloManufacturado " +
                    "LEFT JOIN informacionarticuloventa_insumo ii ON I.idArticuloVenta = ii.idInsumoVenta " +
                    "LEFT JOIN insumo I2 ON ii.idInsumo = I2.idInsumo " +
                    "WHERE idPedido = ? GROUP BY idDetalle ORDER BY idDetalle ASC", (rs, rowNum) -> new DetallePedidoWrapper(
                    rs.getLong("idDetalle"), rs.getInt("cantidad"), new ArticuloVentaWrapper(
                    rs.getString("denominacion"), rs.getFloat("precioVenta")
            )), p.getId())));

            pedidos.forEach(p -> {
                float total = 0F;
                for (DetallePedidoWrapper d : p.getDetalles()) {
                    total += d.getArticulo().getPrecioVenta();
                }
                p.setTotal(total);
            });

            return pedidos;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

}
