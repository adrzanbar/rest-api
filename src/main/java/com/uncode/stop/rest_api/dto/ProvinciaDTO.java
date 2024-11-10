package com.uncode.stop.rest_api.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvinciaDTO {

    private String id;

    @NotBlank
    private String nombre;

    @NotNull
    private UUID paisId;

}
