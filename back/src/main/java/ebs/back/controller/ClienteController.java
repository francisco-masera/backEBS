package ebs.back.controller;

import ebs.back.entity.Cliente;
import ebs.back.entity.Persona;
import ebs.back.service.ClienteService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping(path = "buensabor/cliente")
public class ClienteController extends BaseController<Cliente, ClienteService> {

    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

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


    @PostMapping("/uploadImg")
    @Transactional
    public boolean uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String nombre)
            throws Exception {
        try {
            if (file == null || file.isEmpty()) {
                throw new Exception("El archivo está corrupto o no puede leerse.");
            }

            String upload_folder = ".//src//main//resources//static//images//personas//";
            byte[] filesBytes = file.getBytes();
            Path path = Paths.get(upload_folder + nombre);
            Files.write(path, filesBytes);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @PostMapping("/login")
    @Transactional
    public Object loginCliente(@RequestParam String cuenta, @RequestParam String pass) throws Exception {
        try {

            Persona cliente;
            boolean esEmpleado = !this.jdbcTemplate.queryForList("Select e.idEmpleado FROM Empleado e INNER JOIN Persona p" +
                    " ON e.idEmpleado = p.idPersona AND (email = ? OR usuario = ?)", new Object[]{cuenta, cuenta}, ArrayList.class).isEmpty();
            if (esEmpleado)
                throw new Exception("El proceso de autenticación falló.");
            cliente = this.jdbcTemplate.queryForObject("SELECT * FROM Persona INNER JOIN Cliente C " +
                            "on Persona.idPersona = C.idCliente WHERE (email = ? OR usuario = ?) AND contrasenia = ?",
                    new Object[]{cuenta, cuenta, pass}, (rs, rowNum) ->
                            new Cliente(
                                    rs.getLong("idPersona"),
                                    rs.getString("nombre"),
                                    rs.getString("apellido"),
                                    rs.getString("telefono"),
                                    rs.getString("email"),
                                    rs.getString("foto"),
                                    rs.getString("usuario"),
                                    rs.getString("contrasenia"),
                                    rs.getBoolean("baja"),
                                    null,
                                    null,
                                    null

                            ));

            if (cliente.getBaja())
                throw new Exception("Este usuario no tiene acceso al portal.");
            return cliente;

        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            throw new Exception("No se encontró un usuario registrado con los datos ingresados.", new Throwable(ex.getCause()));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @PostMapping("/gLogin")
    public Object gLogin(@RequestBody Cliente cliente) throws Exception {
        try {
            int existe = this.jdbcTemplate.queryForObject("Select Count(idPersona) From Persona WHERE email = ? AND contrasenia = ?",
                    new Object[]{cliente.getEmail(), ""}, Integer.class);
            boolean esEmpleado = !this.jdbcTemplate.queryForList("Select e.idEmpleado FROM Empleado e INNER JOIN Persona p" +
                    " ON e.idEmpleado = p.idPersona AND email = ?", new Object[]{cliente.getEmail()}, ArrayList.class).isEmpty();
            if (existe == 0 && !esEmpleado)
                this.save(cliente);
            if (esEmpleado)
                throw new Exception("Este usuario ya está registrado como empleado.");
            return this.loginCliente(cliente.getEmail(), "");
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            throw new Exception("No se encontró un usuario registrado con los datos ingresados.", new Throwable(ex.getCause()));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @PutMapping("/generarToken")
    public ResponseEntity<?> generarToken(@RequestParam String email) throws Exception {
        try {
            int existe = this.jdbcTemplate.queryForObject("Select Count(idPersona) From Persona WHERE email = ? AND baja = 0",
                    new Object[]{email}, Integer.class);
            boolean esEmpleado = !this.jdbcTemplate.queryForList("Select e.idEmpleado FROM Empleado e INNER JOIN Persona p" +
                    " ON e.idEmpleado = p.idPersona AND email = ?", new Object[]{email}, ArrayList.class).isEmpty();
            if (existe == 1 && !esEmpleado) {
                int token = Math.abs(new Random().nextInt());
                jdbcTemplate.update("UPDATE Cliente INNER JOIN Persona P on Cliente.idCliente = P.idPersona" +
                        " SET token = ? WHERE email = ?", token, email);

                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("elbuensaborfgr@gmail.com");
                message.setTo(email);
                message.setSubject("Recuperar Contraseña EBS");
                message.setText("Este es tu token para recuperar tu contraseña: " + token +
                        "\nIngrese a http://localhost:8080/ingresoClientes/token=si/email=" + email + " para reestablecer su contraseña");
                configEmail().send(message);

                JSONObject json = new JSONObject();
                json.put("msg", "Se ha enviado un código a su correo para generar una nueva contraseña.");
                return new ResponseEntity<>(json, HttpStatus.OK);

            } else {
                throw new Exception();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("El token no pudo ser generado, reintente.");
        }

    }

    @PutMapping("/recuperarPass")
    public boolean recuperarPass(@RequestParam String pass, @RequestParam String email, @RequestParam int token) throws Exception {
        try {
            int existe = this.jdbcTemplate.queryForObject("Select Count(idPersona) From Persona WHERE email = ? AND baja = 0",
                    new Object[]{email}, Integer.class);
            if (existe != 1) {
                throw new Exception("Su email no se encuentra registrado.");
            }
            boolean esEmpleado = !this.jdbcTemplate.queryForList("Select e.idEmpleado FROM Empleado e INNER JOIN Persona p" +
                    " ON e.idEmpleado = p.idPersona AND email = ?", new Object[]{email}, ArrayList.class).isEmpty();
            if (esEmpleado) {
                throw new Exception("Error en los datos de acceso.");
            }
            try {
                existe = jdbcTemplate.queryForObject("SELECT Count(idPersona) From Persona INNER JOIN Cliente" +
                        " ON Persona.idPersona = Cliente.idCliente WHERE email = ? AND token = ? AND baja = 0", Integer.class, email, token);
                if (existe != 1)
                    throw new Exception("El código es incorrecto.");
            } catch (Exception ex) {
                throw new Exception("El código es incorrecto.");
            }

            jdbcTemplate.update("UPDATE Persona P SET contrasenia = ? WHERE email = ?", pass, email);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

}
