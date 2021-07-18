package ebs.back.controller;

import com.itextpdf.html2pdf.HtmlConverter;
import ebs.back.entity.Factura;
import ebs.back.entity.Pedido;
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
import java.util.Map;
import java.util.Properties;

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
