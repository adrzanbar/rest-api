package com.uncode.stop.rest_api.dto;

import java.io.Serializable;
import java.util.UUID;

import com.uncode.stop.rest_api.entity.TipoEmpleado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoDTO implements Serializable {

    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String legajo;
    @NotNull
    private TipoEmpleado tipoEmpleado;
    private IdentifierDTO<UUID> unidadDeNegocio = new IdentifierDTO<>();

}
