package com.uncode.stop.rest_api.dto;

import java.util.UUID;

import com.uncode.stop.rest_api.entity.EstadoInmueble;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InmuebleDTO {

    private UUID id;
    @NotBlank
    private String numeracion;
    @NotBlank
    private String piso;
    @NotBlank
    private String depto;
    @NotNull
    private EstadoInmueble estadoInmueble;
    @NotNull
    private UUID unidadDeNegocioId;

}
