package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.InformacionInsumoVenta;

@Repository
public interface InformacionInsumoVentaRepository extends JpaRepository<InformacionInsumoVenta, Long> {

}
