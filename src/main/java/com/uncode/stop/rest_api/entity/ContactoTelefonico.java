package com.uncode.stop.rest_api.entity;

import jakarta.persistence.Column;
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
public class ContactoTelefonico extends Contacto {

    @NotBlank
    @Column(nullable = false)
    private String telefono;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTelefono tipoTelefono;
}
