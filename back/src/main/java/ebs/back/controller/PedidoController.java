package ebs.back.controller;

import ebs.back.entity.Cliente;
import ebs.back.entity.Factura;
import ebs.back.entity.Pedido;
import ebs.back.entity.wrapper.*;
import ebs.back.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
    public ResponseEntity<PedidoPendiente> guardarCarrito(@RequestBody PedidoPendiente carrito) {

        Boolean hayPedidoPendiente = existePedido(carrito.getIdCliente());


        PedidoPendiente res;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        if (!hayPedidoPendiente) {
            Time horaEstimada = Time.valueOf(LocalTime.of(0, 0, 0));
            try {
                Long numeroPedido = getUltimoNumeroPedido();
                if (numeroPedido == null)
                    numeroPedido = 1L;
                else
                    numeroPedido += 1;
                AtomicInteger i = new AtomicInteger(1);
                Long finalNumeroPedido = numeroPedido;
                jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement("INSERT INTO Pedido" +
                            " (estado, hora, numero, tipoEntrega, idCliente, formaPago)  VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(i.getAndIncrement(), carrito.getEstado());
                    ps.setTime(i.getAndIncrement(), horaEstimada);
                    ps.setLong(i.getAndIncrement(), finalNumeroPedido);
                    ps.setBoolean(i.getAndIncrement(), carrito.getTipoEntrega());
                    ps.setLong(i.getAndIncrement(), carrito.getIdCliente());
                    ps.setBoolean(i.getAndIncrement(), carrito.getFormaPago());
                    return ps;
                }, keyHolder);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
        try {
            long idPedido;
            if (keyHolder.getKey() != null) {
                idPedido = keyHolder.getKey().longValue();
            } else {
                idPedido = carrito.getIdPedido();
            }
            carrito.getItems().forEach(d -> {
                if (!existeDetalle(d.getIdArticuloVenta(), idPedido))
                    this.jdbcTemplate.update("INSERT INTO DetallePedido (cantidad, idArticulo, idPedido) VALUES(?,?,?)",
                            d.getCantidad(), d.getIdArticuloVenta(), idPedido);
                else
                    this.jdbcTemplate.update(
                            "UPDATE DetallePedido SET cantidad = ? WHERE idDetalle = ? AND idArticulo = ?",
                            d.getCantidad(), d.getIdItem(), d.getIdArticuloVenta());

            });

            res = jdbcTemplate.queryForObject("Select p.idPedido, p.tipoEntrega, p.formaPago, p.estado, p.hora, " +
                    "c.idCliente FROM Pedido p" +
                    " INNER JOIN cliente c on p.idCliente = c.idCliente" +
                    " WHERE p.idPedido = ?", (rs, rowNum) -> new PedidoPendiente(
                    rs.getLong("idPedido"), rs.getLong("idCliente"), rs.getBoolean("formaPago"),
                    rs.getBoolean("tipoEntrega"), rs.getString("estado"), 0L, rs.getTime("hora").toLocalTime(),
                    new ArrayList<>(), carrito.getTiempoEstimado()
            ), idPedido);

            res.setItems(jdbcTemplate.query("SELECT dp.idDetalle, cantidad, idArticulo, " +
                    " a.denominacion, i2.denominacion, i.precioVenta FROM DetallePedido dp " +
                    "INNER JOIN informacionarticuloventa i on dp.idArticulo = i.idArticuloVenta" +
                    " LEFT JOIN articulomanufacturado a on i.idArticuloVenta = a.idArticuloManufacturado" +
                    " LEFT JOIN informacionarticuloventa_insumo ii on i.idArticuloVenta = ii.idInsumoVenta" +
                    " LEFT JOIN insumo i2 on i2.idInsumo = ii.idInsumo" +
                    " Where dp.idPedido = ?", (rs, rowNum) -> new ItemPedidoPendiente(
                    rs.getLong("idDetalle"), rs.getLong("idArticulo"), rs.getInt("cantidad"),
                    rs.getFloat("precioVenta"), rs.getString("denominacion")
            ), idPedido));
            res.setIdPedido(idPedido);
            return ResponseEntity.ok(res);

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
    private ResponseEntity cambiarEstado(@PathVariable String id, @PathVariable String estado) {

        try {

            if (estado.equals("Listo")) {
                List<DetallePedidoWrapper> detalles = jdbcTemplate.query(
                        "Select d.*, i.idArticuloVenta From DetallePedido d INNER JOIN informacionarticuloventa i" +
                                " ON d.idArticulo = i.idArticuloVenta  WHERE idPedido = ?", (rs, rowNum) -> new DetallePedidoWrapper(
                                rs.getLong("idDetalle"), rs.getInt("cantidad"), new ArticuloVentaWrapper(
                                rs.getLong("idArticuloVenta")
                        )), id);
                for (DetallePedidoWrapper d : detalles
                ) {
                    Long idStock = jdbcTemplate.queryForObject(
                            "SELECT idStock FROm insumo WHERE idInsumo = ?", Long.class, d.getArticulo().getIdArticuloVenta());
                    Float actual = jdbcTemplate.queryForObject("Select actual from stock where stock.idStock = ?", Float.class, idStock);
                    if (actual - d.getCantidad() < 0) {

                        return ResponseEntity.badRequest().body("No se puede realizar el pedido por falta de stock");

                    }
                    Long idArticulo = null;

                    try {
                        idArticulo = jdbcTemplate.queryForObject(
                                "Select idArticuloManufacturado FROM articulomanufacturado where idArticuloManufacturado = ?",
                                Long.class, d.getArticulo().getIdArticuloVenta());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (idArticulo == null) {
                        jdbcTemplate.update("UPDATE Stock Set actual = (actual - ?) WHERE stock.idStock = ? ", d.getCantidad(), idStock);

                    } else {

                        jdbcTemplate.query("SELECT idInsumo From receta where  idManufacturado = ?",
                                (rs, rowNum) -> rs.getLong("idInsumo"), idArticulo)
                                .forEach(i -> {
                                    jdbcTemplate.update("UPDATE Stock Set actual = (actual - ?) WHERE stock.idStock = ? ", d.getCantidad(), idStock);
                                });
                    }
                }
            }

            this.jdbcTemplate
                    .update("UPDATE Pedido set estado = " + "'" + estado + "'" + "where idPedido = " + "'" + id + "'");
            return ResponseEntity.ok(true);

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


            List<DecrementoInsumo> decrementoInsumos = jdbcTemplate.query(
                    "SELECT i.idInsumo, r.cantidadInsumo, d.cantidad FROM insumo i inner JOIN receta r on i.idInsumo = r.idInsumo" +
                            " inner  JOIN  articulomanufacturado a on r.idManufacturado = a.idArticuloManufacturado" +
                            " inner JOIN DetallePedido d On d.idArticulo = a.idArticuloManufacturado" +
                            " Where d.idPedido = ?",
                    (rs, rowNum) -> new DecrementoInsumo(
                            rs.getLong("idInsumo"), rs.getBigDecimal("cantidadInsumo"), rs.getInt("cantidad")
                    ),
                    pedido.getIdPedido());

            decrementoInsumos.forEach(i -> {
                Long idStock = jdbcTemplate.query("SELECT idStock from stock where idStock = ?",
                        (rs, n) -> rs.getLong("idStock"), i.getIdInsumo()).get(0);
                BigDecimal dism = jdbcTemplate.query("SELECT actual from stock where idStock = ?",
                        (rs, n) -> BigDecimal.valueOf(rs.getFloat("actual")), idStock).get(0).subtract(
                        i.getCantidad().multiply(BigDecimal.valueOf(i.getCantidadDetalle())));
                jdbcTemplate.update(
                        "UPDATE stock SET actual = ? WHERE idStock = ?"
                        , dism.floatValue(), idStock);
            });

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return true;
    }

    @GetMapping("/pedidosPorCliente")
    public List<PedidoWrapper> pedidosPorCliente(@RequestParam int yMax, @RequestParam int mMax, @RequestParam int dMax,
                                                 @RequestParam int yMin, @RequestParam int mMin, @RequestParam int dMin) throws Exception {
        try {
            Timestamp maxFecha = Timestamp.valueOf(LocalDateTime.of(yMax, mMax, dMax, 0, 0, 0));
            Timestamp minFecha = Timestamp.valueOf(LocalDateTime.of(yMin, mMin, dMin, 0, 0, 0));
            if (minFecha.after(maxFecha)) {
                throw new Exception("La fecha mínima no puede ser mayor a la máxima.");
            }
            List<PedidoWrapper> pedidos = jdbcTemplate.query("SELECT p.idPedido, p.tipoEntrega, F.numero, p2.idPersona, p2.email, " +
                    "CONCAT(p2.nombre, ' ', p2.apellido) as nombreCompleto, " +
                    "F.fechaHora as fechaFacturacion, F.porcentajeDescuento, F.total, F.formaPago FROM Pedido p" +
                    " INNER JOIN cliente c on p.idCliente = c.idCliente" +
                    " INNER JOIN persona p2 on c.idCliente = p2.idPersona" +
                    " INNER JOIN Factura F on p.idPedido = F.idPedido" +
                    " WHERE F.fechaHora BETWEEN ? AND ?" +
                    " GROUP BY p2.idPersona, nombreCompleto ORDER BY nombreCompleto", (rs, rowNum) -> new PedidoWrapper(
                    rs.getLong("idPedido"), 0, "",
                    null, rs.getBoolean("formaPago"), rs.getBoolean("tipoEntrega"),
                    new Factura(
                            0L, rs.getTimestamp("fechaFacturacion").toLocalDateTime(), rs.getLong("numero"),
                            rs.getFloat("porcentajeDescuento"), rs.getFloat("total"),
                            rs.getBoolean("formaPago"), null),
                    new Cliente(
                            rs.getString("nombreCompleto"), rs.getString("email")
                    ), new ArrayList<>(), 0F), minFecha, maxFecha);

            for (PedidoWrapper p : pedidos) {
                List<DetallePedidoWrapper> detalles = jdbcTemplate.query(
                        "Select d.*, i.precioVenta From DetallePedido d INNER JOIN informacionarticuloventa i" +
                                " ON d.idArticulo = i.idArticuloVenta  WHERE idPedido = ?", (rs, rowNum) -> new DetallePedidoWrapper(
                                rs.getLong("idDetalle"), rs.getInt("cantidad"), new ArticuloVentaWrapper(
                                "", rs.getFloat("precioVenta")
                        )), p.getId());
                p.setDetalles(detalles);
            }

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

            for (PedidoWrapper p : pedidos) {
                List<DetallePedidoWrapper> detalles = new ArrayList<>();

                detalles.addAll(jdbcTemplate.query("SELECT DetallePedido.*, a.denominacion, I.precioVenta FROM DetallePedido " +
                        "INNER JOIN informacionarticuloventa I ON DetallePedido.idArticulo = I.idArticuloVenta " +
                        "INNER JOIN articulomanufacturado a ON I.idArticuloVenta = a.idArticuloManufacturado " +
                        "WHERE idPedido = ? GROUP BY idDetalle ORDER BY idDetalle", (rs, rowNum) -> new DetallePedidoWrapper(
                        rs.getLong("idDetalle"), rs.getInt("cantidad"), new ArticuloVentaWrapper(
                        rs.getString("denominacion"), rs.getFloat("precioVenta")
                )), p.getId()));
                detalles.addAll(jdbcTemplate.query("SELECT DetallePedido.*, I2.denominacion, I.precioVenta FROM DetallePedido " +
                        "INNER JOIN informacionarticuloventa I ON DetallePedido.idArticulo = I.idArticuloVenta " +
                        "INNER JOIN informacionarticuloventa_insumo ii ON I.idArticuloVenta = ii.idInsumoVenta " +
                        "INNER JOIN insumo I2 ON ii.idInsumo = I2.idInsumo " +
                        "WHERE idPedido = ? GROUP BY idDetalle ORDER BY idDetalle", (rs, rowNum) -> new DetallePedidoWrapper(
                        rs.getLong("idDetalle"), rs.getInt("cantidad"), new ArticuloVentaWrapper(
                        rs.getString("denominacion"), rs.getFloat("precioVenta")
                )), p.getId()));
                p.setDetalles(detalles);

            }
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
