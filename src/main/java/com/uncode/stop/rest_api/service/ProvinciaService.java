package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Provincia;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.ProvinciaRepository;

@Service
public class ProvinciaService extends CrudService<Provincia, UUID> {

    public ProvinciaService(ProvinciaRepository repository) {
        super(repository);
    }

    @Override
    public void validate(Provincia entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("nombre is required");
        }
    }

}
