package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.HabitanteDTO;
import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.error.NotFoundException;
import com.uncode.stop.rest_api.repository.HabitanteRepository;

@Service
public class HabitanteService extends CRUDService2<Habitante, UUID, HabitanteDTO> {

    private final HabitanteRepository repository;
    private final PersonaService personaService;
    private final ModelMapper modelMapper;

    public HabitanteService(HabitanteRepository repository, PersonaService personaService,
            ModelMapper modelMapper) {
        super(repository);
        this.repository = repository;
        this.personaService = personaService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void validate(Habitante entity) {
        personaService.validate(entity);
    }

    @Override
    protected Habitante toEntity(HabitanteDTO dto) {
        var entity = modelMapper.map(dto, Habitante.class);
        var inmueble = entity.getInmueble();
        if (inmueble != null) {
            inmueble.setId(dto.getInmueble().getId());
        }
        return entity;
    }

    @Override
    protected Habitante toEntity(UUID id, HabitanteDTO dto) {
        var entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Habitante not found"));
        modelMapper.map(dto, entity);
        var inmueble = entity.getInmueble();
        if (inmueble != null) {
            inmueble.setId(dto.getInmueble().getId());
        }
        return entity;
    }

}
