package com.uncode.stop.rest_api.service;

import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.util.EntityUtils;

public abstract class ContactoService {

    public void validate(Contacto entity) {
        EntityUtils.trimStringFields(entity);

        var tipoContacto = entity.getTipoContacto();
        if (tipoContacto == null) {
            throw new ServiceException("TipoContacto required");
        }
    }

}
