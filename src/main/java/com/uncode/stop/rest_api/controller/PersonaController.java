package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.PersonaDTOAdapter;
import com.uncode.stop.rest_api.dto.PersonaDTO;
import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController extends CrudController<Persona, UUID, PersonaDTO> {


    public PersonaController(PersonaService service, PersonaDTOAdapter adapter) {
        super(service, adapter);
    }

}
