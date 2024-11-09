package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.dto.PlanillaHorariaDTO;
import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.entity.PlanillaHoraria;
import com.uncode.stop.rest_api.service.PersonaService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PlanillaHorariaDTOAdapter implements DTOAdapter<PlanillaHoraria, PlanillaHorariaDTO> {

    private final ModelMapper mapper;
    private final PersonaService personaService;

    @Override
    public PlanillaHoraria toEntity(PlanillaHorariaDTO dto) {
        var entity = mapper.map(dto, PlanillaHoraria.class);
        var persona = personaService.readOne(dto.getEmpleadoId());
        if (persona instanceof Empleado) {
            entity.setEmpleado((Empleado) persona);
        }
        return entity;
    }

    @Override
    public PlanillaHorariaDTO toDTO(PlanillaHoraria entity) {
        var dto = mapper.map(entity, PlanillaHorariaDTO.class);
        dto.setEmpleadoId(entity.getEmpleado().getId());
        return dto;
    }

}
