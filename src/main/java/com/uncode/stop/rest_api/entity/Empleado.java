package com.uncode.stop.rest_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Empleado extends Persona {

    @Column(nullable = false)
    private String legajo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipoEmpleado;

}
