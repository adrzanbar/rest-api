package com.uncode.stop.rest_api.service;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.UsuarioDTO;
import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.UsuarioRepository;

@Service
public class UsuarioService extends CRUDService2<Usuario, UUID, UsuarioDTO> {

    private final UsuarioRepository repository;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository repository, ModelMapper modelMapper) {
        super(repository);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void validate(Usuario entity) {
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

    @Override
    protected Usuario toEntity(UsuarioDTO dto) {
        if (!dto.getClave().equals(dto.getConfirmarClave())) {
            throw new ServiceException("confirmarClave must match clave");
        }
        return modelMapper.map(dto, Usuario.class);
    }

    @Override
    protected Usuario toEntity(UUID id, UsuarioDTO dto) {
        var entity = repository.findById(id).orElseThrow(() -> new ServiceException("Usuario not found"));
        if (!dto.getClave().equals(dto.getConfirmarClave())) {
            throw new ServiceException("confirmarClave must match clave");
        }
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, entity);
        modelMapper.getConfiguration().setSkipNullEnabled(false);
        return entity;
    }

    public Optional<Usuario> findByCuenta(String cuenta) {
        return repository.findByCuenta(cuenta);
        //return repository.findByCuenta(cuenta).orElseThrow(() -> new ServiceException("Usuario not found"));
    }


}
