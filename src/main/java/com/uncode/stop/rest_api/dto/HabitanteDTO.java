package com.uncode.stop.rest_api.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitanteDTO {

    private String nombre;
    private String apellido;
    private IdentifierDTO<UUID> inmueble;

}
