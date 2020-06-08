package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.SugerenciaChef;

@Repository
public interface SugerenciaChefRepository extends JpaRepository<SugerenciaChef, Long> {

}
