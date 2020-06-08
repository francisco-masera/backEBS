package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.RubroManufacturado;

@Repository
public interface RubroManufacturadoRepository extends JpaRepository<RubroManufacturado, Long> {

}
