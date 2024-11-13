package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Direccion;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.DireccionRepository;

@Service
public class DireccionService extends CrudService<Direccion, UUID>{

	private final DireccionRepository repository;
	
	public DireccionService(DireccionRepository repository) {
		super(repository);
		this.repository = repository;
	}


	@Override
	public void validate(Direccion entity) {
		// TODO Auto-generated method stub
		var calle = entity.getCalle();
		if (calle == null || calle.isBlank()) {
			throw new ServiceException("calle is required");
		}
		
		var numeracion = entity.getNumeracion();
		if (numeracion == null || numeracion.isBlank()) {
			throw new ServiceException("numeracion is required");
		}
		
		var longitud = entity.getLongitud();
		if (longitud == null || longitud.isBlank()) {
			throw new ServiceException("longitud is required");
		}
		
		var latitud = entity.getLongitud();
		if (latitud == null || latitud.isBlank()) {
			throw new ServiceException("latitud is required");
		}
		
		var localidad = entity.getLocalidad();
		if (localidad == null) {
			throw new ServiceException("localidad is required");
		}
	}
}
