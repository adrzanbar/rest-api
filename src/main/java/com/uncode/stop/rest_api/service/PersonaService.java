package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.error.UniqueConstraintViolationException;
import com.uncode.stop.rest_api.model.Persona;
import com.uncode.stop.rest_api.repository.PersonaRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Getter
public class PersonaService extends CrudService<Persona, UUID> {

    private final PersonaRepository repository;

    @Override
    protected void validate(Persona entity) {
        var existing = repository.findByCorreo(entity.getCorreo());
        if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
            throw new UniqueConstraintViolationException("Correo already exists");
        }
    }

}
