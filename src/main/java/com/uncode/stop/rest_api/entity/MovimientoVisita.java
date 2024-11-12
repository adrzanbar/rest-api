package com.uncode.stop.rest_api.entity;

import com.uncode.stop.rest_api.service.Identifiable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@SoftDelete(columnName = "eliminado")
public class MovimientoVisita implements Identifiable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime fechaMovimiento;

    private String observacion;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoMovimiento estadoMovimiento;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovilidad tipoMovilidad;

    private String descripcionMovilidad;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVisita tipoVisita;

    @NotNull
    @ManyToOne(optional = false)
    private Visitante visitante;

    @ManyToOne(optional = false)
    private Inmueble inmueble;

}
