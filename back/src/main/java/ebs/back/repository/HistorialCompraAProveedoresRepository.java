package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.HistorialCompraAProveedores;

@Repository
public interface HistorialCompraAProveedoresRepository extends JpaRepository<HistorialCompraAProveedores, Long> {

}
