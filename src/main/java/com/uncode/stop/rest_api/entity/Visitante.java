package com.uncode.stop.rest_api.entity;

import com.uncode.stop.rest_api.service.Identifiable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.util.UUID;

@Getter
@Setter
@Entity
@SoftDelete(columnName = "eliminado")
public class Visitante implements Identifiable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String numeroDeDocumento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVisita tipoVisita;

}
