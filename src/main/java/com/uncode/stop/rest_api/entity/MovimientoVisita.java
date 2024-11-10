package com.uncode.stop.rest_api.entity;

import com.uncode.stop.rest_api.service.Identifiable;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @Column(nullable = false)
    private LocalDateTime fechaMovimiento;

    @Column(nullable = false)
    private String observacion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoMovimiento estadoMovimiento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovilidad tipoMovilidad;

    @Column(nullable = false)
    private String descripcionMovilidad;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVisita tipoVisita;

    @ManyToOne(optional = false)
    private Visitante visitante;

}
