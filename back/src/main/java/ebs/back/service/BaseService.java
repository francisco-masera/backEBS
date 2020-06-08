package ebs.back.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<E, R extends JpaRepository<E, Long>> implements IBaseService<E> {

	@Autowired
	protected R repository;

	@Override
	public E findById(Long id) throws Exception {
		try {
			Optional<E> varOptional = repository.findById(id);
			E entity = varOptional.get();
			return entity;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage(), e.getCause());
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e.getCause());
		}
	}
}
