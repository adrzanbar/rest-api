package com.uncode.stop.rest_api.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.uncode.stop.rest_api.entity.Empleado;

@Repository
public interface EmpleadoRepository extends PersonaRepository<Empleado> {

    Optional<Empleado> findByLegajo(String legajo);

}
