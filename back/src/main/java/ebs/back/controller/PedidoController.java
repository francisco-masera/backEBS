package ebs.back.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.ArticuloManufacturado;
import ebs.back.entity.DetallePedido;
import ebs.back.entity.InformacionArticuloVenta;
import ebs.back.entity.Pedido;
import ebs.back.entity.wrapper.CarritoPendiente;
import ebs.back.service.PedidoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping(path = "buensabor/pedido")
public class PedidoController extends BaseController<Pedido, PedidoService> {

    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @GetMapping("/getCarritoByCliente/{idCliente}")
    public CarritoPendiente getCarritoByCliente(@PathVariable Long idCliente) {
        try {
            CarritoPendiente carrito = this.jdbcTemplate
                    .queryForObject("Select * From Pedido " + "WHERE idCliente = ?", new Object[] { idCliente }, (
                            rs, rowNum
                    ) -> new CarritoPendiente(null, null, null, null, null, rs.getBoolean("formaPago"),
                            rs.getBoolean("tipoEntrega"), "", 0L, null, new ArrayList<DetallePedido>()));

            List<DetallePedido> detalles = this.jdbcTemplate.query("", new Object[] { idCliente },
                    (rs, rowNum) -> new DetallePedido(rs.getLong("idDetalle"), rs.getInt("cantidad"), null, null));

            detalles.forEach(d -> {
                InformacionArticuloVenta articulo = this.jdbcTemplate.queryForObject("SELECT IAV.idArticuloVenta, "
                        + "IAV.precioVenta from InformacionArticuloVenta IAV INNER JOIN DetallePedido DP on IAV.idArticuloVenta = DP.idArticulo "
                        + "WHERE DP.idDetalle = ?", new Object[] { d.getId() }, InformacionArticuloVenta.class);
                InformacionArticuloVenta articulo2 = null;
                if (existe(
                        "Select Count(idArticuloManufacturado) From ArticuloManufacturado Where idArticuloManufacturado = ?",
                        articulo.getId())) {
                    articulo2 = jdbcTemplate.queryForObject("SELECT * From ArticuloManufacturado",
                            new Object[] { articulo.getId() }, ArticuloManufacturado.class);

                    articulo2.setId(articulo.getId());
                    articulo2.setPrecioVenta(articulo.getPrecioVenta());

                } else {

                    articulo2 = jdbcTemplate.queryForObject(
                            "SELECT * From informacionarticuloventa_insumo iavi "
                                    + "INNER JOIN Insumo I on iavi.idInsumo = I.idInsumo",
                            new Object[] { articulo.getId() }, ArticuloManufacturado.class);

                    articulo2.setId(articulo.getId());
                    articulo2.setPrecioVenta(articulo.getPrecioVenta());

                }

                d.setArticulo(articulo2);
            });
            carrito.setDetalles(detalles);
            return carrito;

        } catch (Exception ex) {
            throw ex;
        }
    }

    private Boolean existe(String sql, Long id) {
        try {
            return jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class) == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    @PostMapping("/pedidoPendiente")
    private void pedidoPendiente(@RequestParam CarritoPendiente carrito) {

        try {
            this.jdbcTemplate.update(
                    "INSERT INTO Pedido (estado, hora, numero, tipoEntrega, idCliente) VALUES (?,?,?,?,?)",
                    carrito.getEstado(), carrito.getHoraEstimada(), carrito.getNumero(), carrito.getEsDelivery());
            carrito.getDetalles().forEach(d -> {
                this.jdbcTemplate.update("INSERT INTO DetallePedido (cantidad, idArticulo, idPedido) VALUES(?,?,?)",
                        d.getCantidad(), 0, d.getArticulo().getId());

            });
        } catch (Exception ex) {
            throw ex;
        }
    }

    @PutMapping("/pedidoEntregado/{id}/{estado}")
    private void cambiarEstado(@PathVariable String id, @PathVariable String estado) {
        System.out.print("Estoy aca" + id + estado);
        try {
           this.jdbcTemplate.update("UPDATE Pedido set estado = " + "'" + estado + "'" + "where idPedido = " + "'" + id + "'");
        }catch (Exception e) {
            throw e;
        }
        
    }
}
