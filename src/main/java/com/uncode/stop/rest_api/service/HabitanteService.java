package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.HabitanteRepository;

@Service
public class HabitanteService extends CrudService<Habitante, UUID> {

    private final PersonaService personaService;

    public HabitanteService(HabitanteRepository habitanteRepository, PersonaService personaService) {
        super(habitanteRepository);
        this.personaService = personaService;
    }

    @Override
    public void validate(Habitante entity) {
        personaService.validate(entity);

        var inmueble = entity.getInmueble();
        if (inmueble == null || inmueble.getId() == null) {
            throw new ServiceException("inmueble required");
        }
    }

}
