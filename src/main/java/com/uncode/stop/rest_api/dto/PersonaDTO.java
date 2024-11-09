package com.uncode.stop.rest_api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDTO {

    // Persona
    private UUID id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    private UsuarioDTO usuario;
    private List<ContactoDTO> contactos = new ArrayList<>();

    // Empleado
    private String legajo;
    private String tipoEmpleado;

}
