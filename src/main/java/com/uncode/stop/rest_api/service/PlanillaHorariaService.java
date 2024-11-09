package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.PlanillaHorariaDTO;
import com.uncode.stop.rest_api.entity.PlanillaHoraria;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.PlanillaHorariaRepository;

@Service
public class PlanillaHorariaService extends CrudService<PlanillaHoraria, UUID, PlanillaHorariaDTO> {

    private final ModelMapper mapper;
    private final PersonaService personaService;

    public PlanillaHorariaService(PlanillaHorariaRepository repository, ModelMapper mapper,
            PersonaService personaService) {
        super(repository);
        this.mapper = mapper;
        this.personaService = personaService;
    }

    @Override
    public void validate(PlanillaHoraria entity) {
        var entrada = entity.getEntrada();
        if (entrada == null) {
            throw new ServiceException("entrada required");
        }
        var salida = entity.getSalida();
        if (salida == null) {
            throw new ServiceException("salida required");
        }
        if (entrada.isAfter(salida)) {
            throw new ServiceException("entrada must be before salida");
        }
        var estadoAsistencia = entity.getEstadoAsistencia();
        if (estadoAsistencia == null) {
            throw new ServiceException("estadoAsistencia required");
        }
        var empleado = entity.getEmpleado();
        if (empleado == null) {
            throw new ServiceException("empleado required");
        }
        personaService.validate(empleado);
    }

    @Override
    public PlanillaHoraria toEntity(PlanillaHorariaDTO dto) {
        var entity = mapper.map(dto, PlanillaHoraria.class);
        entity.setEmpleado(personaService.readOneEmpleado(dto.getEmpleadoId()));
        return entity;
    }

    @Override
    public PlanillaHorariaDTO toDTO(PlanillaHoraria entity) {
        var dto = mapper.map(entity, PlanillaHorariaDTO.class);
        dto.setEmpleadoId(entity.getEmpleado().getId());
        return dto;
    }

}
