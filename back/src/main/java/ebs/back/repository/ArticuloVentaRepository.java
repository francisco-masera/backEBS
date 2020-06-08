package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.ArticuloVenta;

@Repository
public interface ArticuloVentaRepository extends JpaRepository<ArticuloVenta, Long> {

}
