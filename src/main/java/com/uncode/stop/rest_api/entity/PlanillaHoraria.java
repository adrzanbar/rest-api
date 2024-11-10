package com.uncode.stop.rest_api.entity;

import java.util.UUID;
import java.time.LocalDateTime;

import org.hibernate.annotations.SoftDelete;

import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SoftDelete(columnName = "eliminado")
public class PlanillaHoraria implements Identifiable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime entrada;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime salida;

    @NotNull
    @Column(nullable = false)
    private EstadoAsistencia estadoAsistencia;

    private String observacionAsistencia;

    @NotNull
    @ManyToOne(optional = false)
    private Empleado empleado;

}
