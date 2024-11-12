package com.uncode.stop.rest_api.entity;

import com.uncode.stop.rest_api.service.Identifiable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.util.UUID;

@Getter
@Setter
@Entity
@SoftDelete(columnName = "eliminado")
public class Imagen implements Identifiable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String mime;

    @Lob
    private byte[] contenido;
}
