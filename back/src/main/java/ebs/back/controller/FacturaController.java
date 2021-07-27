package ebs.back.controller;

import com.itextpdf.html2pdf.HtmlConverter;
import ebs.back.entity.Factura;
import ebs.back.entity.Pedido;
import ebs.back.entity.wrapper.Ingreso;
import ebs.back.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping(path = "buensabor/factura")
public class FacturaController extends BaseController<Factura, FacturaService> {

    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @GetMapping("/ingresos")
    public Ingreso ingresos(@RequestParam int yMax, @RequestParam int mMax, @RequestParam int dMax,
                            @RequestParam int yMin, @RequestParam int mMin, @RequestParam int dMin) throws Exception {
        try {
            Timestamp maxFecha = Timestamp.valueOf(LocalDateTime.of(yMax, mMax, dMax, 0, 0, 0));
            Timestamp minFecha = Timestamp.valueOf(LocalDateTime.of(yMin, mMin, dMin, 0, 0, 0));
            if (minFecha.after(maxFecha)) {
                throw new Exception("La fecha mínima no puede ser mayor a la máxima.");
            }
            // List<Double> res = new ArrayList<>();
            return jdbcTemplate.queryForObject("SELECT SUM(f.total) as total FROM Factura f" +
                    " WHERE F.fechaHora BETWEEN ? AND ?", (rs, rowNum) -> new Ingreso(
                    minFecha.toLocalDateTime().toLocalDate(), maxFecha.toLocalDateTime().toLocalDate(), rs.getDouble("total")
            ), minFecha, maxFecha);

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

    private Long getUltimoNumeroFactura() {
        try {
            return jdbcTemplate.queryForObject("SELECT numero FROM Factura ORDER BY idFactura DESC LIMIT 1", Long.class);
        } catch (Exception ex) {
            return null;
        }
    }

    @PostMapping("/generar")
    public void generar(@RequestBody Pedido pedido) {
        try {

            Factura factura = new Factura();

            factura.setFormaPago(pedido.isFormaPago());
            Long numeroFactura = getUltimoNumeroFactura();
            if (numeroFactura == null)
                numeroFactura = 1L;
            else
                numeroFactura += 1;
            factura.setNumero(numeroFactura);
            factura.setFechaHora(LocalDateTime.now());
            double total = pedido.calularTotal();
            if (pedido.isTipoEntrega()) {
                factura.setPorcentajeDescuento(10);
                factura.setTotal(total - total * .10);
            } else {
                factura.setPorcentajeDescuento(0);
                factura.setTotal(total);
            }
            jdbcTemplate.update("INSERT INTO Factura (fechaHora, formaPago, numero, porcentajeDescuento, total, idPedido) " +
                            "VALUES (?,?,?,?,?,?)", factura.getFechaHora(), factura.isFormaPago(), factura.getNumero(), factura.getPorcentajeDescuento(),
                    factura.getTotal(), pedido.getId());


            pedido.getDetalles().forEach(dp -> {
                float costo = 0;
                Long idArticulo = null;
                try {
                    idArticulo = jdbcTemplate.queryForObject(
                            "SELECT i.idArticuloManufacturado FROM articulomanufacturado i" +
                                    " Inner JOIN DetallePedido DP on i.idArticuloManufacturado = DP.idArticulo " +
                                    " WHERE DP.idDetalle= ?", Long.class, dp.getId());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (idArticulo == null) {

                    costo = jdbcTemplate.queryForObject("SELECT precioUnitario FROM historialcompraaproveedores h " +
                            " INNER JOIN insumo i on h.idInsumo = i.idInsumo" +
                            " INNER JOIN informacionarticuloventa_insumo ii on i.idInsumo = ii.idInsumo" +
                            " INNER JOIN DetallePedido DP ON dp.idArticulo = ii.idInsumoVenta" +
                            " WHERE  DP.idDetalle = ?", Float.class, dp.getId());
                    idArticulo = jdbcTemplate.queryForObject(
                            "SELECT i.idInsumoVenta FROM informacionarticuloventa_insumo i" +
                                    " Inner JOIN DetallePedido DP on i.idInsumoVenta = DP.idArticulo " +
                                    " WHERE DP.idDetalle= ?", Long.class, dp.getId());
                } else {
                    costo = getCosto(idArticulo);
                }

                jdbcTemplate.update("INSERT INTO historialventas (costo, fechaVenta, precioVenta, idArticulo) " +
                        "VALUES (?,?,?,?)", costo, factura.getFechaHora(), dp.calcularSubTotal(), idArticulo);
            });

            String email = jdbcTemplate.queryForObject("SELECT email FROM persona where idPersona = ?", String.class, pedido.getCliente().getId());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("elbuensaborfgr@gmail.com");
            message.setTo(email);
            message.setSubject("Factura del día:" + factura.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            String link = "http://localhost:8080/descargarFactura/" + pedido.getId();
            message.setText("Aquí puede descargar su factura:" + link);
            configEmail().send(message);

        } catch (Exception ex) {
            throw ex;
        }
    }


    private Float getCosto(Long idManufacturado) {
        Map<String, Float> insumos = new HashMap<>();
        jdbcTemplate.query(
                "SELECT h.precioUnitario, r.cantidadInsumo FROM insumo INNER JOIN historialcompraaproveedores h on insumo.idInsumo = h.idInsumo" +
                        " INNER JOIN receta r ON  insumo.idInsumo = r.idInsumo " +
                        "Inner JOIN articulomanufacturado a on r.idManufacturado = a.idArticuloManufacturado " +
                        " WHERE a.idArticuloManufacturado = ? Order By h.fechaCompra DESC",
                (rs, rowNum) -> insumos.put(String.valueOf(rs.getFloat("cantidadInsumo")), rs.getFloat("precioUnitario")),
                idManufacturado);

        AtomicReference<Float> sumatoria = new AtomicReference<>(0F);
        insumos.forEach((cantidad, precio) -> {
            sumatoria.updateAndGet(v -> v + Float.parseFloat(cantidad) * precio);
        });
        return sumatoria.get();

    }

    private JavaMailSenderImpl configEmail() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("elbuensaborfgr@gmail.com");
        mailSender.setPassword("ebsfgr2021");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @PostMapping("/descargarFactura")
    public boolean createPdf(@RequestBody Map<String, Object> source) throws IOException {

        try {
            String home = System.getProperty("user.home");
            File f = new File(home + "/Downloads/Factura.pdf");
            HtmlConverter.convertToPdf(source.get("source").toString().replaceAll("\"", ""), new FileOutputStream(f));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
