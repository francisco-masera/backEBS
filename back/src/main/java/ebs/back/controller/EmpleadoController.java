package ebs.back.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ebs.back.entity.Empleado;
import ebs.back.service.EmpleadoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/empleado")
public class EmpleadoController extends BaseController<Empleado, EmpleadoService> {

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

}
