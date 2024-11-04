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
@Getter
public class UsuarioService extends CrudService<Usuario, UUID> {

    private final UsuarioRepository repository;

    @Override
    protected void validate(Usuario entity) {
        try {
            EntityUtils.trimStringFields(entity);
            var existing = repository.findByCuenta(entity.getCuenta());
            if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
                throw new ServiceException("cuenta");
            }
            if (entity.getRol() == null) {
                throw new ServiceException("rol");
            }
        } catch (IllegalAccessException e) {
            throw new ServiceException("error");
        } catch (NullPointerException e) {
            throw new ServiceException("null fields");
        }
    }

}
