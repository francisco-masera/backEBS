package ebs.back.controller;

import ebs.back.entity.Empleado;
import ebs.back.entity.Persona;
import ebs.back.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping(path = "buensabor/empleado")
public class EmpleadoController extends BaseController<Empleado, EmpleadoService> {

    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

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
    public Persona loginEmpleado(
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false, defaultValue = "") String usuario,
            @RequestParam String pass) throws Exception {
        try {
            Persona empleado;
            boolean esCliente = !this.jdbcTemplate.queryForList("Select c.idCliente FROM Cliente c INNER JOIN Persona p" +
                    " ON c.idCliente = p.idPersona AND (email = ? OR usuario=?)", new Object[]{email, usuario}, ArrayList.class).isEmpty();
            if (esCliente)
                throw new Exception("El proceso de autenticación falló.");

            empleado = this.jdbcTemplate.queryForObject("SELECT * FROM Persona p INNER JOIN Empleado e ON " +
                            " p.idPersona = e.idEmpleado" +
                            " WHERE (email = ? OR usuario = ?) AND contrasenia = ? ",
                    new Object[]{email, email, pass}, (rs, rowNum) ->
                            new Empleado(
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
                                    rs.getString("rol")

                            ));
            if (empleado.getBaja())
                throw new Exception("Este usuario no tiene acceso al portal.");
            return empleado;
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            throw new Exception("No se encontró un usuario registrado con los datos ingresados.", new Throwable(ex.getCause()));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @PostMapping("/registro")
    public Object registro(@RequestBody Empleado empleado) throws Exception {
        try {
            int existe = this.jdbcTemplate.queryForObject("Select Count(idPersona) From Persona Where email = ? OR usuario = ?",
                    new Object[]{empleado.getEmail(), empleado.getUsuario()}, Integer.class);
            if (existe != 0)
                throw new Exception("Este usuario o email ya está registrado como cliente.");

            this.save(empleado);

            return this.loginEmpleado(empleado.getEmail(), empleado.getContrasenia(), empleado.getUsuario());
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            throw new Exception("No se encontró un usuario registrado con los datos ingresados.", new Throwable(ex.getCause()));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
