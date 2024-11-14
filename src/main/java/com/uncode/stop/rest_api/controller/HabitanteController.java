package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.HabitanteAdapter;
import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.service.HabitanteService;

@RestController
@RequestMapping("/habitantes")
public class HabitanteController extends CrudController<Habitante, UUID, Habitante> {

    public HabitanteController(HabitanteService service, HabitanteAdapter adapter) {
        super(null, adapter);
    }

}
