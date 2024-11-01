package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.dto.PersonaDTO;
import com.uncode.stop.rest_api.service.CrudService;
import com.uncode.stop.rest_api.service.PersonaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
public class PersonaController extends CrudController<UUID, PersonaDTO> {

    private final PersonaService service;

    @Override
    protected CrudService<?, UUID, PersonaDTO> getService() {
        return service;
    }

}
