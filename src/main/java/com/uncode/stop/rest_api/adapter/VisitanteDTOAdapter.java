package com.uncode.stop.rest_api.adapter;

import com.uncode.stop.rest_api.dto.VisitanteDTO;
import com.uncode.stop.rest_api.entity.Visitante;
import com.uncode.stop.rest_api.service.VisitanteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VisitanteDTOAdapter implements DTOAdapter<Visitante, VisitanteDTO> {

    private final ModelMapper mapper;
    private final VisitanteService visitanteService;

    @Override
    public Visitante toEntity(VisitanteDTO dto) {
        var entity = mapper.map(dto, Visitante.class);
        return entity;
    }

    @Override
    public VisitanteDTO toDTO(Visitante entity){
        return mapper.map(entity, VisitanteDTO.class);
    }
}
