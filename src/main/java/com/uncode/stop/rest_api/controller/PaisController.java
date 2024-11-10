package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.PaisDTOAdapter;
import com.uncode.stop.rest_api.dto.PaisDTO;
import com.uncode.stop.rest_api.entity.Pais;
import com.uncode.stop.rest_api.service.PaisService;

@RestController
@RequestMapping("/paises")
public class PaisController extends CrudController<Pais, UUID, PaisDTO> {

    public PaisController(PaisService service, PaisDTOAdapter adapter) {
        super(service, adapter);
    }
}
