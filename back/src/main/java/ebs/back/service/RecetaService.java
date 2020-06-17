package ebs.back.service;

import java.util.Optional;

import org.springframework.stereotype.Service;


import ebs.back.entity.Receta;
import ebs.back.repository.RecetaRepository;

@Service
public class RecetaService extends BaseService<Receta, RecetaRepository> {

	@Override
	public boolean delete(Long id) throws Exception {
		try {
			
			Receta entity = new Receta();
			if (repository.existsById(id)) {
				Optional<Receta> entityOptional = repository.findById(id);
				entity = entityOptional.get();
				entity.setBaja(true);
				entity = repository.save(entity);
			}
			if(!entity.getBaja()) {
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
