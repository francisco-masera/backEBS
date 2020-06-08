package ebs.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ebs.back.service.IBaseService;

public class BaseController<E, S extends IBaseService<E>> {

	@Autowired
	protected S service;

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					"{\"Error en la solicitud\": \"" + e.getMessage() + "\". Causa raíz\":\"" + e.getCause() + "\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					"{\"Error en la solicitud\": \"" + e.getMessage() + "\". Causa raíz\":\"" + e.getCause() + "\"}");
		}
	}
}
