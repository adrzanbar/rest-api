package com.uncode.stop.rest_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ServicioDTO {

    private UUID id;
    @NotBlank
    private String nombre;
    @NotBlank
    private UUID imagenId;

}
