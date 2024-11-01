package com.uncode.stop.rest_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uncode.stop.rest_api.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, UUID> {

    boolean existsByCorreo(String correo);

}
