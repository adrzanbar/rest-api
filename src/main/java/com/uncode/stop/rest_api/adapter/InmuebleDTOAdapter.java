package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.dto.InmuebleDTO;
import com.uncode.stop.rest_api.entity.Inmueble;
import com.uncode.stop.rest_api.service.UnidadDeNegocioService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InmuebleDTOAdapter implements DTOAdapter<Inmueble, InmuebleDTO> {
    
    private final ModelMapper mapper;
    private final UnidadDeNegocioService unidadDeNegocioService;
    
    @Override
    public Inmueble toEntity(InmuebleDTO dto) {
        var entity = mapper.map(dto, Inmueble.class);
        entity.setUnidadDeNegocio(unidadDeNegocioService.readOne(dto.getUnidadDeNegocioId()));
        return entity;
    }

    @Override
    public InmuebleDTO toDTO(Inmueble entity) {
        var dto = mapper.map(entity, InmuebleDTO.class);
        dto.setUnidadDeNegocioId(entity.getUnidadDeNegocio().getId());
        return dto;
    }

}
