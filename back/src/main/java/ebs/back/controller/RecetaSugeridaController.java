package ebs.back.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.RecetaSugerida;
import ebs.back.entity.wrapper.RecetaSugeridaWrapper;
import ebs.back.service.RecetaSugeridaService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/recetaSugerida")
public class RecetaSugeridaController extends BaseController<RecetaSugerida, RecetaSugeridaService> {

	@Autowired
	private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public ResponseEntity<?> save(RecetaSugerida recetaSugerida) {
		try {
			this.jdbcTemplate.update(
					"INSERT INTO RecetaSugerida (cantidadInsumo, idInsumo, idSugerencia) VALUES (?, ?, ?)",
					recetaSugerida.getCantidadInsumo(), recetaSugerida.getInsumo().getIdInsumo(),
					recetaSugerida.getSugerenciaChef().getId());
			List<RecetaSugeridaWrapper> recetas = this.jdbcTemplate.query(
					"SELECT * FROM RecetaSugerida WHERE idSugerencia = ? ORDER BY idRecetaSugerida",
					new Object[] { recetaSugerida.getSugerenciaChef().getId() },
					(rs, rowNum) -> new RecetaSugeridaWrapper(rs.getLong(1), rs.getFloat(2), rs.getLong(3),
							rs.getLong(4)));
			return ResponseEntity.status(HttpStatus.OK)
					.body(recetas.stream().max(Comparator.comparing(RecetaSugeridaWrapper::getId)).get());
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

	@DeleteMapping("eliminaRecetas/{id}")
	public int deleteRecetas(@PathVariable Long id) {
		int rowsDelete = 0;
		try {
			String query = "DELETE FROM RecetaSugerida where idSugerencia = " + id;
			rowsDelete = jdbcTemplate.update(query);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return rowsDelete;
	}
}
