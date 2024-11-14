package com.uncode.stop.rest_api.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Inmueble;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.InmuebleRepository;

@Service
public class InmuebleService extends CrudService<Inmueble, UUID> {

    private final InmuebleRepository repository;

    public InmuebleService(InmuebleRepository repository, ModelMapper mapper) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void validate(Inmueble entity) {
        var numeracion = entity.getNumeracion();
        if (numeracion == null || numeracion.isBlank()) {
            throw new ServiceException("numeracion required");
        }

        var piso = entity.getPiso();
        if (piso == null || piso.isBlank()) {
            throw new ServiceException("piso required");
        }

        var depto = entity.getDepto();
        if (depto == null || depto.isBlank()) {
            throw new ServiceException("depto required");
        }

        var estadoInmueble = entity.getEstadoInmueble();
        if (estadoInmueble == null) {
            throw new ServiceException("estadoInmueble required");
        }

        var unidadDeNegocio = entity.getUnidadDeNegocio();
        if (unidadDeNegocio == null || unidadDeNegocio.getId() == null) {
            throw new ServiceException("unidadDeNegocio required");
        }

    }

    public Inmueble findByUnidadDeNegocioIdAndNumeracionAndPisoAndDepto(UUID unidadDeNegocioId, String numeracion,
            String piso, String depto) {
        return repository
                .findByUnidadDeNegocioIdAndNumeracionAndPisoAndDepto(unidadDeNegocioId, numeracion, piso, depto)
                .orElseThrow(
                        () -> new ServiceException(
                                "inmueble not found"));
    }
    
    public List<Inmueble> listarInmueblesPorBarrio(UUID id){
    	return repository.findByUnidadDeNegocioId(id);
    }

}
