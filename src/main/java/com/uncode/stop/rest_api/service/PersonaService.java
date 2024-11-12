package com.uncode.stop.rest_api.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.ContactoCorreoElectronico;
import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.EmpleadoRepository;
import com.uncode.stop.rest_api.repository.PersonaRepository;

@Service
public class PersonaService extends CrudService<Persona, UUID> {

    private final PersonaRepository repository;
    private final UsuarioService usuarioService;
    private final ContactoService contactoService;

    public PersonaService(PersonaRepository repository, EmpleadoRepository empleadoRepository,
            UsuarioService usuarioService, ContactoService contactoService, InmuebleService inmuebleService) {
        super(repository);
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.contactoService = contactoService;
    }

    @Override
    public void validate(Persona entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("nombre required");
        }

        var apellido = entity.getApellido();
        if (apellido == null || apellido.isBlank()) {
            throw new ServiceException("apellido required");
        }

        var usuario = entity.getUsuario();
        if (usuario == null) {
            throw new ServiceException("usuario required");
        }

        usuarioService.validate(usuario);

        var contactos = entity.getContactos();
        if (contactos == null) {
            entity.setContactos(new ArrayList<>());
            contactos = entity.getContactos();
        }

        for (var contacto : contactos) {
            contactoService.validate(contacto);
            if (contacto instanceof ContactoCorreoElectronico) {
                var email = ((ContactoCorreoElectronico) contacto).getEmail();
                var existing = repository.findByEmail(email);
                if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
                    throw new ServiceException("email must be unique");
                }
            }
        }
    }

}
