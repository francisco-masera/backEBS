package ebs.back.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ebs.back.entity.Cliente;
import ebs.back.entity.Empleado;
import ebs.back.repository.ClienteRepository;

@Service
public class ClienteService extends BaseService<Cliente, ClienteRepository> {
	@Override
	public boolean delete(Long id) throws Exception {
		try {
			
			Cliente entity = new Cliente();
			if (repository.existsById(id)) {
				Optional<Cliente> entityOptional = repository.findById(id);
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
