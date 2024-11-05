package com.uncode.stop.rest_api.service;

import com.uncode.stop.rest_api.entity.ContactoCorreoElectronico;
import com.uncode.stop.rest_api.error.ServiceException;

public class ContactoCorreoElectronicoService extends ContactoService {

    private final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public void validate(ContactoCorreoElectronico entity) {
        super.validate(entity);

        var correoElectronico = entity.getEmail();
        if (correoElectronico == null || correoElectronico.isBlank()) {
            throw new ServiceException("CorreoElectronico required");
        }
        // https://emailregex.com/
        if (!correoElectronico.matches(EMAIL_REGEX)) {
            throw new ServiceException("Invalid email");
        }
    }

}
