package ebs.back.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ebs.back.entity.Empleado;
import ebs.back.repository.EmpleadoRepository;

@Service
public class EmpleadoService extends BaseService<Empleado, EmpleadoRepository> {
	@Override
	public boolean delete(Long id) throws Exception {
		try {

			Empleado entity = new Empleado();
			if (repository.existsById(id)) {
				Optional<Empleado> entityOptional = repository.findById(id);
				entity = entityOptional.get();
				entity.setBaja(true);
				entity = repository.save(entity);
			}
			if (!entity.getBaja()) {
				return true;
			} else {
				return false;
			}

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
}
