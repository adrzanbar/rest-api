package com.uncode.stop.rest_api.model;

import java.util.UUID;

import org.hibernate.annotations.SoftDelete;

import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@SoftDelete(columnName = "eliminado")
@Data
public class Persona implements Identifiable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "Nombre requerido")
    private String nombre;
    @NotBlank(message = "Apellido requerido")
    private String apellido;
    @NotBlank(message = "Correo requerido")
    @Email(message = "Correo inválido")
    private String correo;
    @NotBlank(message = "Teléfono requerido")
    private String telefono;

}
