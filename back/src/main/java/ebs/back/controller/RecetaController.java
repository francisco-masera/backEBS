package ebs.back.controller;

import java.util.Comparator;
import java.util.List;

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

import ebs.back.entity.Receta;
import ebs.back.entity.wrapper.RecetaSugeridaWrapper;
import ebs.back.service.RecetaService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/receta")
public class RecetaController extends BaseController<Receta, RecetaService> {

	@Autowired
	private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public ResponseEntity<?> save(Receta receta) {
		try {
			this.jdbcTemplate.update("INSERT INTO Receta (cantidadInsumo, idInsumo, idManufacturado) VALUES (?, ?, ?)",
					receta.getCantidadInsumo(), receta.getInsumo().getIdInsumo(), receta.getManufacturado().getId());
			List<RecetaSugeridaWrapper> recetas = this.jdbcTemplate.query(
					"SELECT * FROM Receta WHERE idManufacturado = ? ORDER BY idReceta",
					new Object[] { receta.getManufacturado().getId() },
					(rs, rowNum) -> new RecetaSugeridaWrapper(rs.getLong(1), rs.getFloat(3), rs.getLong(4),
							rs.getLong(4)));
			return ResponseEntity.status(HttpStatus.OK)
					.body(recetas.stream().max(Comparator.comparing(RecetaSugeridaWrapper::getId)).get());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@PostMapping("/guardarReceta/{idManufacturado}")
	public ResponseEntity<?> auxPostReceta(@RequestBody Receta receta, @PathVariable Long idManufacturado) {
		receta.getManufacturado().setId(idManufacturado);
		return this.save(receta);
	}

}
