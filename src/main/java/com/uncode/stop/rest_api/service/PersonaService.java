package com.uncode.stop.rest_api.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.ContactoCorreoElectronico;
import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.EmpleadoRepository;
import com.uncode.stop.rest_api.repository.PersonaRepository;

@Service
public class PersonaService extends CrudService<Persona, UUID> {

    private final PersonaRepository repository;
    private final EmpleadoRepository empleadoRepository;
    private final UsuarioService usuarioService;
    private final ContactoService contactoService;
    private final InmuebleService inmuebleService;

    public PersonaService(PersonaRepository repository, EmpleadoRepository empleadoRepository,
            UsuarioService usuarioService, ContactoService contactoService, InmuebleService inmuebleService) {
        super(repository);
        this.repository = repository;
        this.empleadoRepository = empleadoRepository;
        this.usuarioService = usuarioService;
        this.contactoService = contactoService;
        this.inmuebleService = inmuebleService;
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

        if (entity instanceof Empleado) {
            var empleado = (Empleado) entity;
            var legajo = empleado.getLegajo();
            if (legajo == null || legajo.isBlank()) {
                throw new ServiceException("legajo required");
            }

            var existing = empleadoRepository.findByLegajo(legajo);
            if (existing.isPresent() && !existing.get().getId().equals(empleado.getId())) {
                throw new ServiceException("legajo must be unique");
            }

            var tipoEmpleado = empleado.getTipoEmpleado();
            if (tipoEmpleado == null) {
                throw new ServiceException("tipoEmpleado required");
            }
        } else if (entity instanceof Habitante) {
            var habitante = (Habitante) entity;
            var inmueble = habitante.getInmueble();
            if (inmueble == null || inmueble.getId() == null) {
                throw new ServiceException("inmueble required");
            }
        }
    }

    @Override
    public void resolveRelationships(Persona entity) {
        if (entity instanceof Habitante) {
            var habitante = (Habitante) entity;
            habitante.setInmueble(inmuebleService.readOne(habitante.getInmueble().getId()));
        }
    }

}
