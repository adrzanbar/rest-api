package com.uncode.stop.rest_api.dto;

import com.uncode.stop.rest_api.entity.TipoContacto;
import com.uncode.stop.rest_api.entity.TipoEmpleado;
import com.uncode.stop.rest_api.entity.TipoTelefono;
import com.uncode.stop.rest_api.entity.Usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDTO {

    // Persona
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    private Usuario usuario;

    // Empleado
    private String legajo;
    private TipoEmpleado tipoEmpleado;

    // Contacto
    private TipoContacto tipoContacto;
    private String observacion;

    // ContactoTelefonico
    private String telefono;
    private TipoTelefono tipoTelefono;

    // ContactoCorreoElectronico
    private String email;
}
