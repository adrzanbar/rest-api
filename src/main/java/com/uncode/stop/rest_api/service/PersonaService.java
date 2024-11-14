package com.uncode.stop.rest_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.ContactoDTO;
import com.uncode.stop.rest_api.dto.UsuarioDTO;
import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.error.NotFoundException;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository repository;
    private final UsuarioService usuarioService;
    private final ContactoService contactoService;

    public void validate(Persona entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("nombre required");
        }

        var apellido = entity.getApellido();
        if (apellido == null || apellido.isBlank()) {
            throw new ServiceException("apellido required");
        }
    }

    public Usuario createUsuario(UUID id, UsuarioDTO dto) {
        var empleado = repository.findById(id).orElseThrow(() -> new NotFoundException("Empleado not found"));
        var usuario = usuarioService.create(dto);
        empleado.setUsuario(usuario);
        repository.save(empleado);
        return usuario;
    }

    public Usuario getUsuario(UUID id) {
        var empleado = repository.findById(id).orElseThrow(() -> new NotFoundException("Empleado not found"));
        return empleado.getUsuario();
    }

    public Usuario updateUsuario(UUID id, UsuarioDTO dto) {
        var empleado = repository.findById(id).orElseThrow(() -> new NotFoundException("Empleado not found"));
        var usuario = empleado.getUsuario();
        usuarioService.update(usuario.getId(), dto);
        return usuario;
    }

    public void deleteUsuario(UUID id) {
        var empleado = repository.findById(id).orElseThrow(() -> new NotFoundException("Empleado not found"));
        var usuario = empleado.getUsuario();
        usuarioService.delete(usuario.getId());
    }

    public List<Contacto> getContactos(UUID id) {
        var empleado = repository.findById(id).orElseThrow(() -> new NotFoundException("Empleado not found"));
        return empleado.getContactos();
    }

    public Contacto createContacto(UUID id, ContactoDTO dto) {
        var empleado = repository.findById(id).orElseThrow(() -> new NotFoundException("Empleado not found"));
        var contacto = contactoService.create(dto);
        empleado.getContactos().add(contacto);
        repository.save(empleado);
        return contacto;
    }

    public Contacto updateContacto(UUID id, UUID contactoId, ContactoDTO dto) {
        var empleado = repository.findById(id).orElseThrow(() -> new NotFoundException("Empleado not found"));
        var contacto = empleado.getContactos().stream().filter(c -> c.getId().equals(contactoId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Contacto not found"));
        contactoService.update(contacto.getId(), dto);
        return contacto;
    }

    public void deleteContacto(UUID id, UUID contactoId) {
        var empleado = repository.findById(id).orElseThrow(() -> new NotFoundException("Empleado not found"));
        var contacto = empleado.getContactos().stream().filter(c -> c.getId().equals(contactoId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Contacto not found"));
        contactoService.delete(contacto.getId());
    }

}
