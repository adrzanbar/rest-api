package com.uncode.stop.rest_api.repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.uncode.stop.rest_api.model.Persona;

@NoRepositoryBean
public interface PersonaRepository<T extends Persona> extends JpaRepository<T, UUID> {

    Optional<T> findByCorreo(String correo);

    Optional<T> findByTelefono(String telefono);

}
