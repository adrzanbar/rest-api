package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.InmuebleAdapter;
import com.uncode.stop.rest_api.entity.Inmueble;
import com.uncode.stop.rest_api.service.InmuebleService;

@RestController
@RequestMapping("/inmuebles")
public class InmuebleController extends CrudController<Inmueble, UUID, Inmueble> {

    public InmuebleController(InmuebleService service, InmuebleAdapter adapter) {
        super(service, adapter);
    }

}
