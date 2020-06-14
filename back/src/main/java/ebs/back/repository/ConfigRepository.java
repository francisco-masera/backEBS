package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {

}
