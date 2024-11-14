package com.uncode.stop.rest_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.dto.ContactoDTO;
import com.uncode.stop.rest_api.dto.EmpleadoDTO;
import com.uncode.stop.rest_api.dto.UsuarioDTO;
import com.uncode.stop.rest_api.entity.Contacto;
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

    @GetMapping("/{id}/contactos")
    public List<Contacto> getContactos(@PathVariable UUID id) {
        return service.getContactos(id);
    }

    @PostMapping("/{id}/contactos")
    public Contacto createContacto(@PathVariable UUID id, @Valid @RequestBody ContactoDTO dto) {
        return service.createContacto(id, dto);
    }

    @PutMapping("/{id}/contactos/{contactoId}")
    public Contacto updateContacto(@PathVariable UUID id, @PathVariable UUID contactoId,
            @Valid @RequestBody ContactoDTO dto) {
        return service.updateContacto(id, contactoId, dto);
    }

    @DeleteMapping("/{id}/contactos/{contactoId}")
    public void deleteContacto(@PathVariable UUID id, @PathVariable UUID contactoId) {
        service.deleteContacto(id, contactoId);
    }
}
