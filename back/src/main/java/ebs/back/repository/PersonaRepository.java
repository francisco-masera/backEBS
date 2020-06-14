package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
