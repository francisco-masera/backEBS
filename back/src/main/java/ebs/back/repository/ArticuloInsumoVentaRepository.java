package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.ArticuloInsumoVenta;

@Repository
public interface ArticuloInsumoVentaRepository extends JpaRepository<ArticuloInsumoVenta, Long> {

}
