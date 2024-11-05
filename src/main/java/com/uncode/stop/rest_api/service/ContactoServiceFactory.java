package com.uncode.stop.rest_api.service;

import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.entity.ContactoCorreoElectronico;
import com.uncode.stop.rest_api.entity.ContactoTelefonico;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
@Setter
public class ContactoServiceFactory {

    private final Map<Class<? extends Contacto>, ContactoService<? extends Contacto>> contactoServices;

    public ContactoServiceFactory(ContactoCorreoElectronicoService contactoCorreoElectronicoService,
            ContactoTelefonicoService contactoTelefonicoService) {
        this.contactoServices = new HashMap<>();
        this.contactoServices.put(ContactoCorreoElectronico.class, contactoCorreoElectronicoService);
        this.contactoServices.put(ContactoTelefonico.class, contactoTelefonicoService);
    }

    public ContactoService<? extends Contacto> getService(Class<? extends Contacto> contactoClass) {
        return contactoServices.get(contactoClass);
    }

}
