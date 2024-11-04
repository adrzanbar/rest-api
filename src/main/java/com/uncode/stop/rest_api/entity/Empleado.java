package com.uncode.stop.rest_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Empleado extends Persona {

    @NotBlank
    private String legajo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipoEmpleado;

}
