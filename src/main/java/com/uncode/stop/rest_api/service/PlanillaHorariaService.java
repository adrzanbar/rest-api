package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.PlanillaHoraria;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.PlanillaHorariaRepository;

@Service
public class PlanillaHorariaService extends CrudService<PlanillaHoraria, UUID> {

    public PlanillaHorariaService(PlanillaHorariaRepository repository) {
        super(repository);
    }

    @Override
    public void validate(PlanillaHoraria entity) {
        var entrada = entity.getEntrada();
        if (entrada == null) {
            throw new ServiceException("entrada required");
        }
        var salida = entity.getSalida();
        if (salida == null) {
            throw new ServiceException("salida required");
        }
        if (entrada.isAfter(salida)) {
            throw new ServiceException("entrada must be before salida");
        }
        var estadoAsistencia = entity.getEstadoAsistencia();
        if (estadoAsistencia == null) {
            throw new ServiceException("estadoAsistencia required");
        }
        var empleado = entity.getEmpleado();
        if (empleado == null || empleado.getId() == null) {
            throw new ServiceException("empleado required");
        }
    }

}
