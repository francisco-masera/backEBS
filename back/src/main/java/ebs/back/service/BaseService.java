package ebs.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<E, R extends JpaRepository<E, Long>> implements IBaseService<E> {

	@Autowired
	protected R repository;

	@Override

	public List<E> getAll(int page, int size) throws Exception {
		try {
			Pageable pageable = PageRequest.of(page, size);
			return repository.findAll(pageable).getContent();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public E getOne(Long id) throws Exception {
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
