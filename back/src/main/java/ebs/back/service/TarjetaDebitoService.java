package ebs.back.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ebs.back.entity.TarjetaDebito;
import ebs.back.repository.TarjetaDebitoRepository;

@Service
public class TarjetaDebitoService extends BaseService<TarjetaDebito, TarjetaDebitoRepository> {

	@Override
	public boolean delete(Long id) throws Exception {
		try {
			
			TarjetaDebito entity = new TarjetaDebito();
			if (repository.existsById(id)) {
				Optional<TarjetaDebito> entityOptional = repository.findById(id);
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
