package com.uncode.stop.rest_api.entity;

import java.util.UUID;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import com.uncode.stop.rest_api.service.Identifiable;

@Getter
@Setter
@Entity
@SoftDelete(columnName = "eliminado")
public class Inmueble implements Identifiable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String numeracion;

    @Column(nullable = false)
    private String piso;

    @Column(nullable = false)
    private String depto;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoInmueble estadoInmueble;

    @ManyToOne(optional = false)
    private UnidadDeNegocio unidadDeNegocio;

}
