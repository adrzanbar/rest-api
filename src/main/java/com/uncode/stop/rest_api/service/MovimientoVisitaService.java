package com.uncode.stop.rest_api.service;

import com.uncode.stop.rest_api.entity.MovimientoVisita;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.MovimientoVisitaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MovimientoVisitaService extends CrudService<MovimientoVisita, UUID> {

    public MovimientoVisitaService(MovimientoVisitaRepository repository) {
        super(repository);
    }

    @Override
    public void validate(MovimientoVisita entity) {
        var tipoMovimiento = entity.getTipoMovimiento();
        if (tipoMovimiento == null) {
            throw new ServiceException("tipo movimiento required");
        }

        var fechaMovimiento = entity.getFechaMovimiento();
        if (fechaMovimiento == null) {
            throw new ServiceException("fecha movimiento required");
        }

        var estadoMovimiento = entity.getEstadoMovimiento();
        if (estadoMovimiento == null) {
            throw new ServiceException("estado movimiento required");
        }

        var tipoMovilidad = entity.getTipoMovilidad();
        if (tipoMovilidad == null) {
            throw new ServiceException("tipo movilidad required");
        }

        var visitante = entity.getVisitante();
        if (visitante == null) {
            throw new ServiceException("visitante required");
        }

        var tipoVisita = entity.getTipoVisita();
        if (tipoVisita == null) {
            throw new ServiceException("tipo visita required");
        }

        var inmueble = entity.getInmueble();

        if (inmueble == null) {
            throw new ServiceException("inmueble required");
        }

    }

}
