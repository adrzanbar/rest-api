package com.uncode.stop.rest_api.service;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService implements Validator<Usuario> {

    private final UsuarioRepository repository;

    @Override
    public void validate(Usuario entity) {
        var cuenta = entity.getCuenta();
        if (cuenta == null || cuenta.isBlank()) {
            throw new ServiceException("cuenta required");
        }

        var clave = entity.getClave();
        if (clave == null || clave.isBlank()) {
            throw new ServiceException("clave required");
        }

        var rol = entity.getRol();
        if (rol == null) {
            throw new ServiceException("rol required");
        }

        var existing = repository.findByCuenta(cuenta);
        if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
            throw new ServiceException("cuenta must be unique");
        }

    }

}
