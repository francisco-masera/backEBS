package ebs.back.controller;

import ebs.back.entity.Domicilio;
import ebs.back.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping(path = "buensabor/domicilio")
public class DomicilioController extends BaseController<Domicilio, DomicilioService> {


    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @GetMapping("getByIDPersona/{idUsuario}")
    public List<Domicilio> getByIDPersona(@PathVariable Long idUsuario) {
        try {
            return jdbcTemplate.query("SELECT * FROM Domicilio WHERE idPersona = ? AND baja = false", new Object[]{idUsuario}, (rs, rowNum) -> new Domicilio(
                    rs.getLong("idDomicilio"), rs.getString("localidad"), rs.getString("calle"), rs.getInt("numero"),
                    rs.getInt("piso"), rs.getBoolean("baja"), rs.getString("departamento"), null, "", ""
            ));
        } catch (EmptyResultDataAccessException ex) {
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

}
