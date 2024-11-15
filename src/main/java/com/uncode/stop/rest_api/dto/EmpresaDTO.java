package com.uncode.stop.rest_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaDTO {

    @NotBlank
    private String nombre;
}
