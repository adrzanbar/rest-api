package com.uncode.stop.rest_api.adapter;

import com.uncode.stop.rest_api.dto.MovimientoVisitaDTO;
import com.uncode.stop.rest_api.entity.MovimientoVisita;
import com.uncode.stop.rest_api.service.VisitanteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovimientoVisitaDTOAdapter implements DTOAdapter<MovimientoVisita, MovimientoVisitaDTO> {

    private final ModelMapper mapper;
    private final VisitanteService visitanteService;

    @Override
    public MovimientoVisitaDTO toDTO(MovimientoVisita entity) {
        var dto = mapper.map(entity, MovimientoVisitaDTO.class);
        dto.setVisitanteId(entity.getVisitante().getId());
        return dto;
    }

    @Override
    public MovimientoVisita toEntity(MovimientoVisitaDTO dto) {
        var entity = mapper.map(dto, MovimientoVisita.class);
        entity.setVisitante(visitanteService.readOne(dto.getVisitanteId()));
        return entity;
    }

}
