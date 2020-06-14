package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
