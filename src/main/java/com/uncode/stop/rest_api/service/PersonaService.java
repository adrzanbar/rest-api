package com.uncode.stop.rest_api.service;

import java.util.UUID;

import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.PersonaRepository;
import com.uncode.stop.rest_api.util.EntityUtils;

public abstract class PersonaService<T extends Persona> extends CrudService<T, UUID> {

    protected abstract PersonaRepository<T> getRepository();

    protected abstract ContactoServiceFactory getContactoServiceFactory();

    @Override
    protected void validate(T entity) {
        try {
            EntityUtils.trimStringFields(entity);
            if (entity.getNombre().isBlank() || entity.getApellido().isBlank()) {
                throw new ServiceException("blank fields");
            }
            getContactoServiceFactory().getService(entity.getContacto().getClass()).validate(entity.getContacto());
        } catch (NullPointerException e) {
            throw new ServiceException("null fields");
        } catch (IllegalAccessException e) {
            throw new ServiceException("error");
        }

    }

}
