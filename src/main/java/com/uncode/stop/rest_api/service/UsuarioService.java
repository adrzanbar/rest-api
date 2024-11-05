package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.UsuarioRepository;
import com.uncode.stop.rest_api.util.EntityUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService extends CrudService<Usuario, UUID> {

    @Getter
    private final UsuarioRepository repository;

    @Override
    protected void validate(Usuario entity) {
        EntityUtils.trimStringFields(entity);

        var cuenta = entity.getCuenta();
        if (cuenta == null || cuenta.isBlank()) {
            throw new ServiceException("Cuenta is required");
        }

        var existing = repository.findByCuenta(cuenta);
        if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
            throw new ServiceException("Cuenta already exists");
        }

        if (entity.getRol() == null) {
            throw new ServiceException("Rol is required");
        }
    }

}
