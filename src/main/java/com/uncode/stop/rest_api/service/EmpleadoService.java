package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.EmpleadoRepository;

@Service
public class EmpleadoService extends CrudService<Empleado, UUID> {

    private final EmpleadoRepository empleadoRepository;
    private final PersonaService personaService;

    public EmpleadoService(EmpleadoRepository empleadoRepository, PersonaService personaService) {
        super(empleadoRepository);
        this.empleadoRepository = empleadoRepository;
        this.personaService = personaService;
    }

    @Override
    public void validate(Empleado entity) {
        personaService.validate(entity);

        var legajo = entity.getLegajo();
        if (legajo == null || legajo.isBlank()) {
            throw new ServiceException("legajo required");
        }

        var existing = empleadoRepository.findByLegajo(legajo);
        if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
            throw new ServiceException("legajo must be unique");
        }

        var tipoEmpleado = entity.getTipoEmpleado();
        if (tipoEmpleado == null) {
            throw new ServiceException("tipoEmpleado required");
        }
    }

}
