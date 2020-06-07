package ebs.back.repository;

import org.springframework.data.repository.CrudRepository;

import ebs.back.entity.BaseEntity;

public interface BaseRepository<T extends BaseEntity> extends CrudRepository<T, Long> {

}
