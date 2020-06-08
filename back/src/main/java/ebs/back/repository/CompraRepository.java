package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

}
