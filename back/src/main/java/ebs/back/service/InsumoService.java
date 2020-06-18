package ebs.back.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ebs.back.entity.Insumo;
import ebs.back.repository.InsumoRepository;

@Service
public class InsumoService extends BaseService<Insumo, InsumoRepository> {


	@Override
	public boolean delete(Long id) throws Exception {
		try {
			
			Insumo entity = new Insumo();
			if (repository.existsById(id)) {
				Optional<Insumo> entityOptional = repository.findById(id);
				entity = entityOptional.get();
				entity.setBaja(true);
				entity = repository.save(entity);
			}
			if(entity.isBaja()) {
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
