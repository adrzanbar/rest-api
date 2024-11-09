package com.uncode.stop.rest_api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.uncode.stop.rest_api.entity.EstadoAsistencia;
import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanillaHorariaDTO implements Identifiable<UUID> {

    private UUID id;
    @NotNull
    private LocalDateTime entrada;
    private LocalDateTime salida;
    @NotNull
    private EstadoAsistencia estadoAsistencia;
    private String observacionAsistencia;
    @NotNull
    private UUID empleadoId;
}
