package com.uncode.stop.rest_api.service;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.EmpleadoRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Service
public class EmpleadoService extends PersonaService<Empleado> {

    private final EmpleadoRepository repository;

    @Override
    protected void validate(Empleado entity) {
        if (entity.getTipoEmpleado() == null) {
            throw new ServiceException("tipoEmpleado");
        }
        super.validate(entity);
    }
}
