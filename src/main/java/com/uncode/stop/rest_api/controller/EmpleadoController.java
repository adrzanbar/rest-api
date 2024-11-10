package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.IdentityAdapter;
import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.service.EmpleadoService;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController extends CrudController<Empleado, UUID, Empleado> {

    public EmpleadoController(EmpleadoService service, IdentityAdapter<Empleado> adapter) {
        super(service, adapter);
    }

}
