package com.uncode.stop.rest_api.dto;

import com.uncode.stop.rest_api.entity.TipoVisita;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VisitanteDTO {

    private UUID id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String numeroDeDocumento;
    @NotNull
    private TipoVisita tipoVisita;
}
