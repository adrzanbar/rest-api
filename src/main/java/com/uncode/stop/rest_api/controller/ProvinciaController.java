package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.adapter.ProvinciaAdapter;
import com.uncode.stop.rest_api.entity.Provincia;
import com.uncode.stop.rest_api.service.ProvinciaService;

@Component
public class ProvinciaController extends CrudController<Provincia, UUID, Provincia> {

    public ProvinciaController(ProvinciaService service, ProvinciaAdapter adapter) {
        super(service, adapter);
    }

}
