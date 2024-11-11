package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.DepartamentoAdapter;
import com.uncode.stop.rest_api.entity.Departamento;
import com.uncode.stop.rest_api.service.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController extends CrudController<Departamento, UUID, Departamento>{

	
	public DepartamentoController(DepartamentoService service, DepartamentoAdapter adapter) {
		super(service, adapter);
	}

}
