package com.uncode.stop.rest_api.dto;

import com.uncode.stop.rest_api.entity.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class MovimientoVisitaDTO {

    private UUID id;
    @NotNull
    private TipoMovimiento tipoMovimiento;
    @NotNull
    private LocalDateTime fechaMovimiento;
    private String observacion;
    @NotNull
    private EstadoMovimiento estadoMovimiento;
    @NotNull
    private TipoMovilidad tipoMovilidad;
    private String descripcionMovilidad;
    @NotNull
    private TipoVisita tipoVisita;
    @NotNull
    private UUID visitanteId;
    @NotNull
    private UUID inmuebleId;
}
