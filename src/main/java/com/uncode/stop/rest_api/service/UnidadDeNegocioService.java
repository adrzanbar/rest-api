package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.UnidadDeNegocio;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.UnidadDeNegocioRepository;

@Service
public class UnidadDeNegocioService extends CrudService<UnidadDeNegocio, UUID> {
	
	private final UnidadDeNegocioRepository repository;
	
    public UnidadDeNegocioService(UnidadDeNegocioRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void validate(UnidadDeNegocio entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("nombre required");
        }
        
        // var direccion = entity.getDireccion();
        // if (direccion == null) {
        // 	throw new ServiceException("direccion required");
		// }
        
    }

}
