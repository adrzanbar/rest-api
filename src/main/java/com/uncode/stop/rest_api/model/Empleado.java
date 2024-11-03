package com.uncode.stop.rest_api.model;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@SoftDelete
@Getter
@Setter
public class Empleado extends Persona {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEmpleado tipoEmpleado;

}
