package ebs.back.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import ebs.back.entity.BaseEntity;

public interface BaseService<T extends BaseEntity> {

	public abstract T save(T entity);

	public abstract List<T> findAll();

	public abstract Optional<T> findById(Long id);

	public abstract T update(T entity);

	public abstract void delete(Query q);

}
