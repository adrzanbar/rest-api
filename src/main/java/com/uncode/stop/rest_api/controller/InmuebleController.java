package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.InmuebleDTOAdapter;
import com.uncode.stop.rest_api.dto.InmuebleDTO;
import com.uncode.stop.rest_api.entity.Inmueble;
import com.uncode.stop.rest_api.service.InmuebleService;

@RestController
@RequestMapping("/inmuebles")
public class InmuebleController extends CrudController<Inmueble, UUID, InmuebleDTO> {

    public InmuebleController(InmuebleService service, InmuebleDTOAdapter adapter) {
        super(service, adapter);
    }

}
