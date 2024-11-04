package com.uncode.stop.rest_api.entity;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@SoftDelete
@Getter
@Setter
public class Empleado extends Persona {

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipoEmpleado;

}
