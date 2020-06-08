package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.RubroInsumo;

@Repository
public interface RubroInsumoRepository extends JpaRepository<RubroInsumo, Long> {

}
