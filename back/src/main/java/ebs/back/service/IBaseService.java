package ebs.back.service;

import java.util.List;

import javax.persistence.Query;

public interface IBaseService<E> {

	public abstract E save(E entity) throws Exception;

	public abstract List<E> getAll(int page, int size) throws Exception;

	public abstract E getOne(Long id) throws Exception;

	public abstract E update(E entity, Long id) throws Exception;

	public abstract boolean delete(Query q, Long id) throws Exception;

}
