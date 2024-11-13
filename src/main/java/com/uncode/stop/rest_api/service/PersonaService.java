package com.uncode.stop.rest_api.service;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.error.ServiceException;

@Service
public class PersonaService {

    public void validate(Persona entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("nombre required");
        }

        var apellido = entity.getApellido();
        if (apellido == null || apellido.isBlank()) {
            throw new ServiceException("apellido required");
        }
    }
}
