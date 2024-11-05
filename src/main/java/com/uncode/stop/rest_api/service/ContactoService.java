package com.uncode.stop.rest_api.service;

import java.util.UUID;

import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.ContactoRepository;
import com.uncode.stop.rest_api.util.EntityUtils;

public abstract class ContactoService<T extends Contacto> extends CrudService<T, UUID> {

    protected abstract ContactoRepository<T> getRepository();

    @Override
    protected void validate(Contacto entity) {
        try {
            EntityUtils.trimStringFields(entity);
        } catch (IllegalAccessException e) {
        }
        if (entity.getTipoContacto() == null) {
            throw new ServiceException("null fields");
        }
    }

}
