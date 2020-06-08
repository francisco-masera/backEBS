package ebs.back.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ebs.back.service.IBaseService;

public class BaseController<E, S extends IBaseService<E>> {

	@Autowired
	protected S service;

	@GetMapping("/")
	@Transactional
	public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getAll(page, size));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.setErrorMessage(e));
		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getOne(id));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.setErrorMessage(e));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.setErrorMessage(e));
		}
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity<?> save(@RequestBody E entity) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.setErrorMessage(e));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.setErrorMessage(e));
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> put(@RequestBody E personaForm, @PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.update(personaForm, id));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.setErrorMessage(e));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"Error en la solicitud\": \"" + e.getMessage() + "\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.setErrorMessage(e));
		}
	}

	private String setErrorMessage(Exception e) {
		return e.getCause() != null
				? "{\"Error en la solicitud\": \"" + e.getMessage() + "\". Causa raíz\":\"" + e.getCause() + "\"}"
				: "{\"Error en la solicitud\": \"" + e.getMessage() + "\"}";
	}
}
