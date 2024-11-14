package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.HabitanteDTO;
import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.repository.HabitanteRepository;

@Service
public class HabitanteService extends CRUDService2<Habitante, UUID, HabitanteDTO> {

    private final PersonaService personaService;

    public HabitanteService(HabitanteRepository habitanteRepository, PersonaService personaService) {
        super(habitanteRepository);
        this.personaService = personaService;
    }

    @Override
    public void validate(Habitante entity) {
        personaService.validate(entity);
    }

    @Override
    protected Habitante toEntity(HabitanteDTO dto) {
        return null;
    }

    @Override
    protected Habitante toEntity(UUID id, HabitanteDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }

}
