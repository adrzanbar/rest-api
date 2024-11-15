package com.uncode.stop.rest_api.dto;

import jakarta.validation.constraints.NotBlank;

public class PersonaDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    
}
