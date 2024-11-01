package com.uncode.stop.rest_api.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonaDTO {

    private UUID id;
    @NotBlank(message = "Nombre requerido")
    private String nombre;
    @NotBlank(message = "Apellido requerido")
    private String apellido;
    @NotBlank(message = "Correo requerido")
    @Email(message = "Correo inválido")
    private String correo;
    @NotBlank(message = "Teléfono requerido")
    private String telefono;
}
