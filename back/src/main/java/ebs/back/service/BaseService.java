package ebs.back.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

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
			return varOptional.get();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public E save(E entity) throws Exception {
		try {
			return repository.save(entity);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public E update(E entity, Long id) throws Exception {
		try {
			Optional<E> entityOptional = repository.findById(id);
			E auxE = entityOptional.get();
			auxE = repository.save(entity);
			return auxE;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean delete(Query q, Long id) throws Exception {
		try {
			if (repository.existsById(id)) {
				q.executeUpdate();
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return !repository.existsById(id);
	}
}
