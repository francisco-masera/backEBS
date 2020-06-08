package ebs.back.service;

import java.util.List;

import javax.persistence.Query;

public interface IBaseService<E> {

	public abstract E save(E entity) throws Exception;

	public abstract List<E> findAll(int page, int size) throws Exception;

	public abstract E findById(Long id) throws Exception;

	public abstract E update(E entity, Long id) throws Exception;

	public abstract void delete(Query q) throws Exception;

	public int countPages(int size) throws Exception;
}
