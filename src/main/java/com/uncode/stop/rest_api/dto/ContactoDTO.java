package com.uncode.stop.rest_api.dto;

import java.util.UUID;

import com.uncode.stop.rest_api.entity.TipoContacto;
import com.uncode.stop.rest_api.entity.TipoTelefono;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactoDTO {

    // Contacto
    private UUID id;
    @NotNull
    private TipoContacto tipoContacto;
    private String observacion;

    // ContactoCorreoElectronico
    @Email
    private String email;

    // ContactoTelefonico
    private String telefono;
    private TipoTelefono tipoTelefono;

}
