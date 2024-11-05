package com.uncode.stop.rest_api.service;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.repository.HabitanteRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Service
public class HabitanteService extends PersonaService<Habitante> {

    private final HabitanteRepository repository;
	private final ContactoServiceFactory contactoServiceFactory;

}
