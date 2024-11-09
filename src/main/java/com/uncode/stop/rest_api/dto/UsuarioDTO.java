package com.uncode.stop.rest_api.dto;

import java.util.UUID;

import com.uncode.stop.rest_api.entity.Rol;
import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO implements Identifiable<UUID> {

    private UUID id;
    @NotBlank
    private String cuenta;
    @NotBlank
    private String clave;
    @NotNull
    private Rol rol;

}