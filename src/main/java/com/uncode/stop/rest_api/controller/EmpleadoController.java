package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.dto.EmpleadoDTO;
import com.uncode.stop.rest_api.dto.UsuarioDTO;
import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.service.EmpleadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController extends EntityController<Empleado, UUID, EmpleadoDTO> {

    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/{id}/usuario")
    public Usuario createUsuario(@PathVariable UUID id, @Valid @RequestBody UsuarioDTO dto) {
        return service.createUsuario(id, dto);
    }

    @GetMapping("/{id}/usuario")
    public Usuario getUsuario(@PathVariable UUID id) {
        return service.getUsuario(id);
    }

    @PutMapping("/{id}/usuario")
    public Usuario updateUsuario(@PathVariable UUID id, @Valid @RequestBody UsuarioDTO dto) {
        return service.updateUsuario(id, dto);
    }

    @DeleteMapping("/{id}/usuario")
    public void deleteUsuario(@PathVariable UUID id) {
        service.deleteUsuario(id);
    }
}
