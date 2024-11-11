package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Provincia;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.ProvinciaRepository;

@Service
public class ProvinciaService extends CrudService<Provincia, UUID> {

	private final ProvinciaRepository repository;
	
    public ProvinciaService(ProvinciaRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void validate(Provincia entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("nombre is required");
        }
        
		var existing = repository.findByNombre(nombre);
		if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
			throw new ServiceException("nombre already exists");
		}
		
		var pais = entity.getPais();
		if (pais == null) {
			throw new ServiceException("pais is required");
		}
    }

}
