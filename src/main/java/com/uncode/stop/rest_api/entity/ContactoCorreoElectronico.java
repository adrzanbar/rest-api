package com.uncode.stop.rest_api.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ContactoCorreoElectronico extends Contacto {

    private String email;
}
