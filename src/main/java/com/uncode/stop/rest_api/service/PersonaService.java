package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.model.Persona;
import com.uncode.stop.rest_api.repository.PersonaRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Service
public class PersonaService<T extends Persona> extends CrudService<T, UUID> {

    private final PersonaRepository<T> repository;

    private void trim(T entity) {
        try {
            entity.setNombre(entity.getNombre().trim());
            entity.setApellido(entity.getApellido().trim());
            entity.setCorreo(entity.getCorreo().trim());
            entity.setTelefono(entity.getTelefono().trim());
        } catch (NullPointerException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    protected void validate(T entity) {
        trim(entity);
        try {
            if (entity.getNombre().isBlank() || entity.getApellido().isBlank() || entity.getCorreo().isBlank()
                    || entity.getTelefono().isBlank()) {
                throw new ServiceException("Invalid field blank");
            }

            // https://emailregex.com/
            if (!entity.getCorreo().matches(
                    "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                throw new ServiceException("Invalid field email");
            }

            var existing = repository.findByCorreo(entity.getCorreo());
            if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
                throw new ServiceException("Invalid field correo exists");
            }

            existing = repository.findByTelefono(entity.getTelefono());
            if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
                throw new ServiceException("Invalid field");
            }
        } catch (NullPointerException e) {
            throw new ServiceException("Required fields are missing");
        }

    }

}
