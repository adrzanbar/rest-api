package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.dto.ProvinciaDTO;
import com.uncode.stop.rest_api.entity.Provincia;
import com.uncode.stop.rest_api.service.PaisService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProvinciaDTOAdapter implements DTOAdapter<Provincia, ProvinciaDTO> {

    private final ModelMapper mapper;
    private final PaisService paisService;

    @Override
    public ProvinciaDTO toDTO(Provincia entity) {
        var dto = mapper.map(entity, ProvinciaDTO.class);
        dto.setPais(entity.getPais());
        return dto;
    }

    @Override
    public Provincia toEntity(ProvinciaDTO dto) {
        var entity = mapper.map(dto, Provincia.class);
        entity.setPais(paisService.readOne(dto.getPais().getId()));
        return entity;
    }

}
