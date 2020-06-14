package ebs.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.back.entity.TarjetaDebito;

@Repository
public interface TarjetaDebitoRepository extends JpaRepository<TarjetaDebito, Long> {

}
