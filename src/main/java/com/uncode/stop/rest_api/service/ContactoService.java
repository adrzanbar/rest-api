package com.uncode.stop.rest_api.service;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.entity.ContactoCorreoElectronico;
import com.uncode.stop.rest_api.entity.ContactoTelefonico;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.ContactoCorreoElectronicoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ContactoService implements Validator<Contacto> {

    private final ContactoCorreoElectronicoRepository contactoCorreoElectronicoRepository;

    // https://emailregex.com/
    private final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    public void validate(Contacto entity) {
        if (entity.getTipoContacto() == null) {
                throw new ServiceException("tipoContacto required");
            }

            if (entity instanceof ContactoTelefonico) {
                var telefono = ((ContactoTelefonico) entity).getTelefono();
                if (telefono == null || telefono.isBlank()) {
                    throw new ServiceException("telefono required");
                }

                var tipoTelefono = ((ContactoTelefonico) entity).getTipoTelefono();
                if (tipoTelefono == null) {
                    throw new ServiceException("tipoTelefono required");
                }
            } else if (entity instanceof ContactoCorreoElectronico) {
                var email = ((ContactoCorreoElectronico) entity).getEmail();
                if (email == null || email.isBlank()) {
                    throw new ServiceException("email required");
                }

                if (!email.matches(EMAIL_REGEX)) {
                    throw new ServiceException("email invalid");
                }

                var existing = contactoCorreoElectronicoRepository.findByEmail(email);
                if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
                    throw new ServiceException("email must be unique");
                }
 
            }
    }

}
