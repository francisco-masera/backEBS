package ebs.back.service;

import java.util.Optional;

import org.springframework.stereotype.Service;


import ebs.back.entity.Domicilio;
import ebs.back.repository.DomicilioRepository;

@Service
public class DomicilioService extends BaseService<Domicilio, DomicilioRepository> {


	@Override
	public boolean delete(Long id) throws Exception {
		try {
			
			Domicilio entity = new Domicilio();
			if (repository.existsById(id)) {
				Optional<Domicilio> entityOptional = repository.findById(id);
				entity = entityOptional.get();
				entity.setBaja(true);
				entity = repository.save(entity);
			}
			if(!entity.isBaja()) {
				return true;
			}else {
				return false;
			}
		
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
}
