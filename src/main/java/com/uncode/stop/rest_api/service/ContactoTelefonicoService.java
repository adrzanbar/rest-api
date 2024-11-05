package com.uncode.stop.rest_api.service;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.ContactoTelefonico;
import com.uncode.stop.rest_api.error.ServiceException;

@Service
public class ContactoTelefonicoService extends ContactoService {

    public void validate(ContactoTelefonico entity) {
        super.validate(entity);

        var telefono = entity.getTelefono();
        if (telefono == null || telefono.isBlank()) {
            throw new ServiceException("Telefono required");
        }

        var tipoTelefono = entity.getTipoTelefono();
        if (tipoTelefono == null) {
            throw new ServiceException("TipoTelefono required");
        }

    }
}
