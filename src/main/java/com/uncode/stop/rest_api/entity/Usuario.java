package com.uncode.stop.rest_api.entity;

import java.util.UUID;

import org.hibernate.annotations.SoftDelete;

import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SoftDelete(columnName = "eliminado")
public class Usuario implements Identifiable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(unique = true)
    private String cuenta;

    @NotBlank
    @Column(nullable = false)
    private String clave;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

}
