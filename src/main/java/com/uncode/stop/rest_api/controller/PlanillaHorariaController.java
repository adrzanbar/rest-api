package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.IdentityAdapter;
import com.uncode.stop.rest_api.entity.PlanillaHoraria;
import com.uncode.stop.rest_api.service.PlanillaHorariaService;

@RestController
@RequestMapping("/planillas-horarias")
public class PlanillaHorariaController extends CrudController<PlanillaHoraria, UUID, PlanillaHoraria> {

    public PlanillaHorariaController(PlanillaHorariaService service, IdentityAdapter<PlanillaHoraria> adapter) {
        super(service, adapter);
    }

}
