package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloVentaRepository extends JpaRepository<ArticuloVentaRepository, Long> {

}
