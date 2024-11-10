package com.uncode.stop.rest_api.controller;

import com.uncode.stop.rest_api.adapter.MovimientoVisitaDTOAdapter;
import com.uncode.stop.rest_api.dto.MovimientoVisitaDTO;
import com.uncode.stop.rest_api.entity.Inmueble;
import com.uncode.stop.rest_api.entity.MovimientoVisita;
import com.uncode.stop.rest_api.service.MovimientoVisitaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/visita")
public class MovimientoVisitaController extends CrudController<MovimientoVisita, UUID, MovimientoVisitaDTO> {

    public MovimientoVisitaController(MovimientoVisitaService service, MovimientoVisitaDTOAdapter adapter){
        super(service, adapter);
    }

}
