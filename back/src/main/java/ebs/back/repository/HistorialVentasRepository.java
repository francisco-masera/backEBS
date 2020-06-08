package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.HistorialVentas;

@Repository
public interface HistorialVentasRepository extends JpaRepository<HistorialVentas, Long> {

}
