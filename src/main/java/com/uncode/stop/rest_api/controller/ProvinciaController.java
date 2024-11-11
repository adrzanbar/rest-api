package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.ProvinciaAdapter;
import com.uncode.stop.rest_api.entity.Provincia;
import com.uncode.stop.rest_api.service.ProvinciaService;

@RestController
@RequestMapping("/provincias")
public class ProvinciaController extends CrudController<Provincia, UUID, Provincia> {

    public ProvinciaController(ProvinciaService service, ProvinciaAdapter adapter) {
        super(service, adapter);
    }

}
