package ebs.back.controller;

import ebs.back.entity.Cliente;
import ebs.back.entity.Persona;
import ebs.back.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping(path = "buensabor/cliente")
public class ClienteController extends BaseController<Cliente, ClienteService> {

    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @PostMapping("/uploadImg")
    @Transactional
    public boolean uploadFile(MultipartFile file, RedirectAttributes attributes)
            throws IOException {
        if (file == null || file.isEmpty()) {
            return false;
        }

        String upload_folder = ".//src//main//resources//static//images//personas//";
        byte[] filesBytes = file.getBytes();
        Path path = Paths.get(upload_folder + file.getOriginalFilename());
        Files.write(path, filesBytes);

        return true;
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

}
