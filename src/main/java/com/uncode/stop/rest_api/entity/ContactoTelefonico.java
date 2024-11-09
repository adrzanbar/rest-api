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
public class ContactoTelefonico extends Contacto {

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTelefono tipoTelefono;
}
