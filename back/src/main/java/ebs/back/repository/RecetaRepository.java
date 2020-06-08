package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

}
