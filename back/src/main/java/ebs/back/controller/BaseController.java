package ebs.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					"{\"Error en la solicitud\": \"" + e.getMessage() + "\". Causa raíz\":\"" + e.getCause() + "\"}");
		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getOne(id));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					"{\"Error en la solicitud\": \"" + e.getMessage() + "\". Causa raíz\":\"" + e.getCause() + "\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					"{\"Error en la solicitud\": \"" + e.getMessage() + "\". Causa raíz\":\"" + e.getCause() + "\"}");
		}
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity<?> post(@RequestBody E entity) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					"{\"Error en la solicitud\": \"" + e.getMessage() + "\". Causa raíz\":\"" + e.getCause() + "\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					"{\"Error en la solicitud\": \"" + e.getMessage() + "\". Causa raíz\":\"" + e.getCause() + "\"}");
		}
	}
}
