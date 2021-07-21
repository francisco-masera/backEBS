package ebs.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.Persona;
import ebs.back.service.PersonaService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping(path = "buensabor/persona")
public class PersonaController extends BaseController<Persona, PersonaService> {

    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @GetMapping("/validarContrasenia")
    public boolean validarContrasenia(@RequestParam Long id, @RequestParam String password) {
        String contrasenia = this.jdbcTemplate.queryForObject("SELECT contrasenia FROM Persona WHERE idPersona=?",
                new Object[]{id}, String.class);
        return contrasenia.equals(password);
    }

    @GetMapping("/verificarEmail")
    public boolean verificarEmail(@RequestParam String email) throws Exception {
        try {
            int existe = this.jdbcTemplate.queryForObject("SELECT Count(email) FROM Persona WHERE email=?",
                    Integer.class, email);
            if (existe != 0) throw new Exception("El e-mail ya está registrado");
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
