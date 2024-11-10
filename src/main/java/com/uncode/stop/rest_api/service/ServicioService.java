package com.uncode.stop.rest_api.service;

import com.uncode.stop.rest_api.entity.Servicio;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServicioService extends CrudService<Servicio, UUID> {

    public ServicioService(ServicioRepository repository) {
        super(repository);
    }

    @Override
    public void validate(Servicio entity) {
        var nombre = entity.getNombre();

        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("nombre required");
        }

        var descripcion = entity.getDescripcion();

        if (descripcion == null || descripcion.isBlank()) {
            throw new ServiceException("descripcion required");
        }

        var fotoUrl = entity.getFotoUrl();

        if (fotoUrl == null || fotoUrl.isBlank()) {
            throw new ServiceException("fotoUrl required");
        }

    }

    @Override
    public void resolveRelationships(Servicio entity) {
    }

}
