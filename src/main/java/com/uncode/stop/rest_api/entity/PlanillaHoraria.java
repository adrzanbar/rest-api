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

    @Column(nullable = false)
    private LocalDateTime entrada;

    @Column(nullable = false)
    private LocalDateTime salida;

    @Column(nullable = false)
    private EstadoAsistencia estadoAsistencia;

    private String observacionAsistencia;

    @ManyToOne(optional = false)
    private Empleado empleado;

}
