package com.uncode.stop.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.entity.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{

    Optional<Usuario> findByCuenta(String cuenta);

}
