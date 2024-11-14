package com.uncode.stop.rest_api.controller;

import com.uncode.stop.rest_api.dto.HabitanteDTO;
import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.service.HabitanteService;
import com.uncode.stop.rest_api.service.PersonaService;

public class HabitanteController extends PersonaController<Habitante, HabitanteDTO> {

    public HabitanteController(HabitanteService service, PersonaService personaService) {
        super(service, personaService);
    }
}
