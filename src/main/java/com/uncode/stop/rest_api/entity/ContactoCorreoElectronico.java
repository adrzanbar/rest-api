package com.uncode.stop.rest_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ContactoCorreoElectronico extends Contacto {

    @Column(nullable = false)
    private String email;

}
