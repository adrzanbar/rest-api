package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.IdentityAdapter;
import com.uncode.stop.rest_api.entity.UnidadDeNegocio;
import com.uncode.stop.rest_api.service.UnidadDeNegocioService;

@RestController
@RequestMapping("/unidades-de-negocio")
public class UnidadDeNegocioController extends CrudController<UnidadDeNegocio, UUID, UnidadDeNegocio> {

    public UnidadDeNegocioController(UnidadDeNegocioService service, IdentityAdapter<UnidadDeNegocio> adapter) {
        super(service, adapter);
    }

}
