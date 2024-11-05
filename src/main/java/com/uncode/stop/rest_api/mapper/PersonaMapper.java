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

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@RequiredArgsConstructor
@Getter
@Setter
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

        Contacto contacto = null;
        if (dto.getTelefono() != null) {
            contacto = new ContactoTelefonico();
            ((ContactoTelefonico) contacto).setTelefono(dto.getTelefono());
            ((ContactoTelefonico) contacto).setTipoTelefono(dto.getTipoTelefono());
        } else if (dto.getEmail() != null) {
            contacto = new ContactoCorreoElectronico();
            ((ContactoCorreoElectronico) contacto).setEmail(dto.getEmail());
        }

        Persona persona = null;
        if (dto.getLegajo() != null) {
            persona = mapper.map(dto, Empleado.class);
            ((Empleado) persona).setContacto(contacto);
        } else {
            persona = mapper.map(dto, Habitante.class);
            ((Habitante) persona).setContacto(contacto);
        }
        return persona;
    }

}
