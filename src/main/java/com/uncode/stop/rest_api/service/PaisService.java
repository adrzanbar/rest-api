package com.uncode.stop.rest_api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Pais;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.PaisRepository;

@Service
public class PaisService extends CrudService<Pais, UUID>{
	
	private final PaisRepository repository;
	
	public PaisService(PaisRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public void validate(Pais entity) {
		
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("nombre required");
        }
	
        Optional<Pais> response = repository.findByName(entity.getNombre());
        if (response.isPresent()) {
        	throw new ServiceException("El pais con ese nombre ya existe.");
		}
	}
	
}
