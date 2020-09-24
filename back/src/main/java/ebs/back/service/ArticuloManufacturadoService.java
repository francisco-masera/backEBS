package ebs.back.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ebs.back.entity.ArticuloManufacturado;
import ebs.back.repository.ArticuloManufacturadoRepository;

@Service
public class ArticuloManufacturadoService extends BaseService<ArticuloManufacturado, ArticuloManufacturadoRepository> {

	@Override
	public boolean delete(Long id) throws Exception {
		try {
			
			ArticuloManufacturado entity = new ArticuloManufacturado();
			if (repository.existsById(id)) {
				Optional<ArticuloManufacturado> entityOptional = repository.findById(id);
				entity = entityOptional.get();
				boolean baja = entity.isBaja();
				entity.setBaja(!baja);
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
