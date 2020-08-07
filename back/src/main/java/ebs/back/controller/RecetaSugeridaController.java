package ebs.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.RecetaSugerida;
import ebs.back.service.RecetaSugeridaService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/recetaSugerida")
public class RecetaSugeridaController extends BaseController<RecetaSugerida, RecetaSugeridaService> {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public ResponseEntity<?> save(RecetaSugerida recetaSugerida) {
		try {
			jdbcTemplate.update("INSERT INTO recetasugerida (cantidadInsumo, idInsumo, idSugerencia) VALUES (?, ?, ?)",
					recetaSugerida.getCantidadInsumo(), recetaSugerida.getInsumo().getIdInsumo(),
					recetaSugerida.getSugerenciaChef().getId());
			return ResponseEntity.status(HttpStatus.OK).body(recetaSugerida);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@PostMapping("/guardarReceta/{idSugerencia}")
	public ResponseEntity<?> auxPostReceta(@RequestBody RecetaSugerida receta, @PathVariable Long idSugerencia) {
		receta.getSugerenciaChef().setId(idSugerencia);
		return this.save(receta);
	}
}
