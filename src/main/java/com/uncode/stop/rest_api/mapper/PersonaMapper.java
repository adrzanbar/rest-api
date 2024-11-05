package com.uncode.stop.rest_api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.dto.PersonaDTO;
import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.entity.ContactoCorreoElectronico;
import com.uncode.stop.rest_api.entity.ContactoTelefonico;
import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.error.ServiceException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonaMapper implements DtoMapper<Persona, PersonaDTO> {

    private final ModelMapper mapper;

    @Override
    public PersonaDTO toDto(Persona entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }

    @Override
    public Persona toEntity(PersonaDTO dto) {
        if (dto.getTelefono() != null && dto.getEmail() != null) {
            throw new ServiceException("Both telefono and email cannot be set");
        }

        Persona persona = null;
        if (dto.getLegajo() != null) {
            persona = mapper.map(dto, Empleado.class);
        } else {
            persona = mapper.map(dto, Habitante.class);
        }

        Contacto contacto = null;

        var email = dto.getEmail();
        if (email != null) {
            contacto = new ContactoCorreoElectronico();
            ((ContactoCorreoElectronico) contacto).setEmail(email);
        } else {
            var telefono = dto.getTelefono();
            if (telefono != null) {
                contacto = new ContactoTelefonico();
                ((ContactoTelefonico) contacto).setTelefono(telefono);
                ((ContactoTelefonico) contacto).setTipoTelefono(dto.getTipoTelefono());
            }
        }

        persona.setContacto(contacto);
        return persona;
    }

}
