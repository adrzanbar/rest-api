package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.mapper.DtoMapper;
import com.uncode.stop.rest_api.mapper.IdentityMapper;
import com.uncode.stop.rest_api.model.Persona;
import com.uncode.stop.rest_api.service.CrudService;
import com.uncode.stop.rest_api.service.PersonaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
public class PersonaController extends CrudController<Persona, UUID, Persona> {

    private final PersonaService service;
    private final IdentityMapper<Persona> mapper;

    @Override
    protected CrudService<Persona, UUID> getService() {
        return service;
    }

    @Override
    protected DtoMapper<Persona, Persona> getMapper() {
        return mapper;
    }

}
