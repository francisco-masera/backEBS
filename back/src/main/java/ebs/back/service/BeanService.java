package ebs.back.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebs.back.repository.Repository;

@Service
public class BeanService {

	@Autowired
	private Repository repository;

	@Transactional
	public List<Object> findAll() throws Exception {
		try {
			List<Object> entities = repository.findAll();
			return entities;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
}
