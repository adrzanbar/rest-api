package com.uncode.stop.rest_api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.entity.ContactoCorreoElectronico;
import com.uncode.stop.rest_api.entity.ContactoTelefonico;
import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.PersonaRepository;
import com.uncode.stop.rest_api.util.EntityUtils;

public abstract class PersonaService<T extends Persona> extends CrudService<T, UUID> {

    private final UsuarioService usuarioService;
    private final Map<Class<? extends Contacto>, ContactoService> contactoServices = new HashMap<>();

    protected PersonaService(UsuarioService usuarioService, ContactoTelefonicoService contactoTelefonicoService,
            ContactoCorreoElectronicoService contactoEmailService) {
        this.usuarioService = usuarioService;
        contactoServices.put(ContactoTelefonico.class, contactoTelefonicoService);
        contactoServices.put(ContactoCorreoElectronico.class, contactoEmailService);
    }

    protected abstract PersonaRepository<T> getRepository();

    @Override
    protected void validate(T entity) {
        EntityUtils.trimStringFields(entity);

        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("Nombre required");
        }

        var apellido = entity.getApellido();
        if (apellido == null || apellido.isBlank()) {
            throw new ServiceException("Apellido required");
        }

        var contacto = entity.getContacto();
        if (contacto != null) {
            contactoServices.get(contacto.getClass()).validate(contacto);
        }

        var usuario = entity.getUsuario();
        if (usuario != null) {
            usuarioService.validate(usuario);
        }
    }
}
