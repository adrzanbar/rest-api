package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.mapper.IdentityMapper;
import com.uncode.stop.rest_api.model.Persona;
import com.uncode.stop.rest_api.service.PersonaService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
@Getter
public class PersonaController extends CrudController<Persona, UUID, Persona> {

    private final PersonaService service;
    private final IdentityMapper<Persona> mapper;
    private final PagedResourcesAssembler<Persona> assembler;

}
