package com.uncode.stop.rest_api.controller;

import com.uncode.stop.rest_api.adapter.VisitanteDTOAdapter;
import com.uncode.stop.rest_api.dto.VisitanteDTO;
import com.uncode.stop.rest_api.entity.Visitante;
import com.uncode.stop.rest_api.service.VisitanteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/visitantes")
public class VisitanteController extends CrudController<Visitante, UUID, VisitanteDTO> {

    public VisitanteController(VisitanteService service, VisitanteDTOAdapter adapter) {
        super(service, adapter);
    }
}
