package com.uncode.stop.rest_api.service;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.EmpleadoRepository;

import lombok.Getter;

@Service
public class EmpleadoService extends PersonaService<Empleado> {

    @Getter
    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository, UsuarioService usuarioService,
            ContactoTelefonicoService contactoTelefonicoService,
            ContactoCorreoElectronicoService contactoEmailService) {
        super(usuarioService, contactoTelefonicoService, contactoEmailService);
        this.repository = repository;
    }

    @Override
    protected void validate(Empleado entity) {
        super.validate(entity);
        try {
            var existing = repository.findByLegajo(entity.getLegajo());
            if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
                throw new ServiceException("legajo");
            }
        } catch (NullPointerException e) {
            throw new ServiceException("null fields");
        }
        if (entity.getTipoEmpleado() == null) {
            throw new ServiceException("tipoEmpleado");
        }
    }
}
