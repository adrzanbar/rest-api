package com.uncode.stop.rest_api.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.PersonaDTO;
import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.entity.ContactoCorreoElectronico;
import com.uncode.stop.rest_api.entity.ContactoTelefonico;
import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService extends CrudService<Persona, UUID, PersonaDTO> {

    private final PersonaRepository repository;
    private final ModelMapper mapper;
    private final EmpleadoRepository empleadoRepository;
    // https://emailregex.com/
    private final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    public void validate(Persona object) {
        var nombre = object.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("nombre required");
        }

        var apellido = object.getApellido();
        if (apellido == null || apellido.isBlank()) {
            throw new ServiceException("apellido required");
        }

        var usuario = object.getUsuario();
        if (usuario == null) {
            throw new ServiceException("usuario required");
        }

        var cuenta = usuario.getCuenta();
        if (cuenta == null || cuenta.isBlank()) {
            throw new ServiceException("cuenta required");
        }

        var clave = usuario.getClave();
        if (clave == null || clave.isBlank()) {
            throw new ServiceException("clave required");
        }

        var rol = usuario.getRol();
        if (rol == null) {
            throw new ServiceException("rol required");
        }

        var contactos = object.getContactos();
        if (contactos == null) {
            object.setContactos(new ArrayList<>());
        }

        for (var contacto : contactos) {
            if (contacto.getTipoContacto() == null) {
                throw new ServiceException("tipoContacto required");
            }

            if (contacto instanceof ContactoTelefonico) {
                var telefono = ((ContactoTelefonico) contacto).getTelefono();
                if (telefono == null || telefono.isBlank()) {
                    throw new ServiceException("telefono required");
                }

                var tipoTelefono = ((ContactoTelefonico) contacto).getTipoTelefono();
                if (tipoTelefono == null) {
                    throw new ServiceException("tipoTelefono required");
                }
            } else if (contacto instanceof ContactoCorreoElectronico) {
                var email = ((ContactoCorreoElectronico) contacto).getEmail();
                if (email == null || email.isBlank()) {
                    throw new ServiceException("email required");
                }

                if (!email.matches(EMAIL_REGEX)) {
                    throw new ServiceException("email invalid");
                }

                var existing = repository.findByEmail(email);
                if (existing.isPresent() && !existing.get().getId().equals(object.getId())) {
                    throw new ServiceException("email must be unique");
                }
            }
        }

        if (object instanceof Empleado) {
            var empleado = (Empleado) object;
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
        }
    }

    @Override
    public Persona toEntity(PersonaDTO dto) {
        Persona persona;
        if (dto.getLegajo() != null) {
            persona = mapper.map(dto, Empleado.class);
        } else {
            persona = mapper.map(dto, Habitante.class);
        }

        persona.setUsuario(mapper.map(dto, Usuario.class));

        var contactos = dto.getContactos();
        var mappedContactos = new ArrayList<Contacto>();
        for (var contacto : contactos) {
            var email = contacto.getEmail();
            var telefono = contacto.getTelefono();
            if (email != null) {
                mappedContactos.add(mapper.map(contacto, ContactoCorreoElectronico.class));
            } else if (telefono != null) {
                mappedContactos.add(mapper.map(contacto, ContactoTelefonico.class));
            }
        }
        persona.setContactos(mappedContactos);
        
        return persona;
    }

    @Override
    public PersonaDTO toDTO(Persona entity) {
        return mapper.map(entity, PersonaDTO.class);
    }

}
