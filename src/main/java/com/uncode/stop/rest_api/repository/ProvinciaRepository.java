package com.uncode.stop.rest_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.entity.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {
	
	Optional<Provincia> findByNombre(String nombre);
}
