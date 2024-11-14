package com.uncode.stop.rest_api.dto;

import com.uncode.stop.rest_api.entity.Rol;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    @NotBlank
    private String cuenta;
    @NotBlank
    private String clave;
    private String confirmarClave;
    @NotNull
    private Rol rol;
}
