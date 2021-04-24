package ebs.back.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ebs.back.entity.Cliente;
import ebs.back.service.ClienteService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/cliente")
public class ClienteController extends BaseController<Cliente, ClienteService> {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@PostMapping("/uploadImg")
	@Transactional
	public boolean uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes)
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
	public ResponseEntity<?> loginCliente(@RequestParam String email, String contrasenia) {
		try {
			int existe = this.jdbcTemplate.queryForObject(
					"SELECT COUNT(*) FROM Persona WHERE email = ? AND contrasenia = ?",
					new Object[] { email, contrasenia }, Integer.class);
			if (existe != 1)
				throw new Exception("Más de un usuario coincidente.");
			return this.getOne(this.jdbcTemplate.queryForObject(
					"SELECT idPersona FROM Persona WHERE email = ? AND contrasenia = ?",
					new Object[] { email, contrasenia }, Long.class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
