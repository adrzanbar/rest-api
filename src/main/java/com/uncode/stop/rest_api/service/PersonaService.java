package com.uncode.stop.rest_api.service;

import java.util.UUID;

import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.PersonaRepository;
import com.uncode.stop.rest_api.util.EntityUtils;

public abstract class PersonaService<T extends Persona> extends CrudService<T, UUID> {

    protected abstract PersonaRepository<T> getRepository();

    @Override
    protected void validate(T entity) {
        try {
            EntityUtils.trimStringFields(entity);

            if (entity.getNombre().isBlank() || entity.getApellido().isBlank() || entity.getCorreo().isBlank()
                    || entity.getTelefono().isBlank()) {
                throw new ServiceException("blank fields");
            }

            // https://emailregex.com/
            if (!entity.getCorreo().matches(
                    "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                throw new ServiceException("correo");
            }

            var existing = getRepository().findByCorreo(entity.getCorreo());
            if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
                throw new ServiceException("correo");
            }

            existing = getRepository().findByTelefono(entity.getTelefono());
            if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
                throw new ServiceException("telefono");
            }
        } catch (NullPointerException e) {
            throw new ServiceException("null fields");
        } catch (IllegalAccessException e) {
            throw new ServiceException("error");
        }

    }

}
