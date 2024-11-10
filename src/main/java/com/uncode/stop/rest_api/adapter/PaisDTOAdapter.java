package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.dto.PaisDTO;
import com.uncode.stop.rest_api.entity.Pais;
import com.uncode.stop.rest_api.service.InmuebleService;
import com.uncode.stop.rest_api.service.PaisService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaisDTOAdapter implements DTOAdapter<Pais, PaisDTO>{
    private final ModelMapper mapper;
    
	@Override
	public PaisDTO toDTO(Pais entity) {
		return mapper.map(entity, PaisDTO.class);
	}
	
	@Override
	public Pais toEntity(PaisDTO dto) {
		return mapper.map(dto, Pais.class);
	}
    
    
    
}
