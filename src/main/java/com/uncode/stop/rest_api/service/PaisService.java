package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Pais;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.PaisRepository;

@Service
public class PaisService extends CrudService<Pais, UUID>{
	
	public PaisService(PaisRepository repository) {
		super(repository);
	}

	@Override
	public void validate(Pais entity) {
		
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("nombre required");
        }
        
	}
	
}
