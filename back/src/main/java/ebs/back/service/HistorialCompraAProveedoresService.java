package ebs.back.service;

import org.springframework.stereotype.Service;

import ebs.back.entity.HistorialCompraAProveedores;
import ebs.back.repository.HistorialCompraAProveedoresRepository;

@Service
public class HistorialCompraAProveedoresService
		extends BaseService<HistorialCompraAProveedores, HistorialCompraAProveedoresRepository> {
	
	@Override
	public HistorialCompraAProveedores save(HistorialCompraAProveedores entity) throws Exception {
		try {
			return repository.save(entity);
		
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
