package ebs.back.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import ebs.back.entity.BaseEntity;
import ebs.back.service.BaseService;

public class BaseRepositoryImpl<T extends BaseEntity> implements BaseService<T> {
	private BaseRepository<T> baseRepository;

	public BaseRepositoryImpl(BaseRepository<T> baseRepository) {
		this.baseRepository = baseRepository;
	}

	@Override
	public T save(T entity) {
		return (T) this.baseRepository.save(entity);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.baseRepository.findAll();
	}

	@Override
	public Optional<T> findById(Long id) {
		return this.baseRepository.findById(id);
	}

	@Override
	public T update(T entity) {
		return this.baseRepository.save(entity);
	}

	public void delete(Query q) {
		q.executeUpdate();
	}

}
