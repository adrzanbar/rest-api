package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.mapper.IdentityMapper;
import com.uncode.stop.rest_api.model.Empleado;
import com.uncode.stop.rest_api.service.EmpleadoService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
@Getter
public class EmpleadoController extends CrudController<Empleado, UUID, Empleado> {

    private final EmpleadoService service;
    private final IdentityMapper<Empleado> mapper;

}
