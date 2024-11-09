package com.uncode.stop.rest_api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.uncode.stop.rest_api.entity.TipoEmpleado;
import com.uncode.stop.rest_api.entity.Usuario;

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
    private Usuario usuario;
    private List<ContactoDTO> contactos = new ArrayList<>();

    // Empleado
    private String legajo;
    private TipoEmpleado tipoEmpleado;

    //Habitante
    private UUID inmuebleId;

}
