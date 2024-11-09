package com.uncode.stop.rest_api.adapter;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.dto.PersonaDTO;
import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.entity.ContactoCorreoElectronico;
import com.uncode.stop.rest_api.entity.ContactoTelefonico;
import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.service.InmuebleService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonaDTOAdapter implements DTOAdapter<Persona, PersonaDTO> {

    private final ModelMapper mapper;
    private final InmuebleService inmuebleService;

    @Override
    public Persona toEntity(PersonaDTO dto) {
        Persona persona;
        if (dto.getLegajo() != null) {
            persona = mapper.map(dto, Empleado.class);
        } else if (dto.getInmuebleId() != null) {
            persona = mapper.map(dto, Habitante.class);
            ((Habitante) persona).setInmueble(inmuebleService.readOne(dto.getInmuebleId()));
        } else {
            persona = mapper.map(dto, Persona.class);
        }

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
