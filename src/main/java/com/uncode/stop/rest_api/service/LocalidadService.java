package com.uncode.stop.rest_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Localidad;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.LocalidadRepository;

@Service
public class LocalidadService extends CrudService<Localidad, UUID>{

	private final LocalidadRepository repository;
	
	public LocalidadService(LocalidadRepository repository) {
		super(repository);
		this.repository = repository;
	}


	@Override
	public void validate(Localidad entity) {
		
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("nombre is required");
        }
        
        var codigoPostal = entity.getCodigoPostal();
        if (codigoPostal == null || codigoPostal.isBlank()) {
        	throw new ServiceException("codigo postal is required");
		}
        
		var existing = repository.findByNombre(nombre);
		if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
			throw new ServiceException("nombre already exists");
		}
        
        var departamento = entity.getDepartamento();
        if (departamento == null) {
        	throw new ServiceException("departamento is required");
		}
	}
	
	public List<Localidad> listarLocalidadesPorDepartamento(UUID id) {
		return repository.findByDepartamentoId(id);
	}
	
}
