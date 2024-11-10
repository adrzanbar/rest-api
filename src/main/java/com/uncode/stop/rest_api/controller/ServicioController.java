package com.uncode.stop.rest_api.controller;

import com.uncode.stop.rest_api.adapter.IdentityAdapter;
import com.uncode.stop.rest_api.entity.Servicio;
import com.uncode.stop.rest_api.service.ServicioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/servicio")
public class ServicioController extends CrudController<Servicio, UUID, Servicio> {

    public ServicioController(ServicioService service, IdentityAdapter<Servicio> adapter) {
        super(service, adapter);
    }

}
