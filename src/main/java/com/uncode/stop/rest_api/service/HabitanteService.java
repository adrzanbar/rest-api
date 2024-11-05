package com.uncode.stop.rest_api.service;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.repository.HabitanteRepository;

import lombok.Getter;

@Service
public class HabitanteService extends PersonaService<Habitante> {

    @Getter
    private final HabitanteRepository repository;

    public HabitanteService(UsuarioService usuarioService, ContactoTelefonicoService contactoTelefonicoService,
            ContactoCorreoElectronicoService contactoEmailService, HabitanteRepository repository) {
        super(usuarioService, contactoTelefonicoService, contactoEmailService);
        this.repository = repository;
    }

}
