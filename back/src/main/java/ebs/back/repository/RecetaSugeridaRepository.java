package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.RecetaSugerida;

@Repository
public interface RecetaSugeridaRepository extends JpaRepository<RecetaSugerida, Long> {

}
