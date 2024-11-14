package com.uncode.stop.rest_api.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.ContactoDTO;
import com.uncode.stop.rest_api.dto.EmpleadoDTO;
import com.uncode.stop.rest_api.dto.UsuarioDTO;
import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.error.NotFoundException;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.EmpleadoRepository;

@Service
public class EmpleadoService extends CRUDService2<Empleado, UUID, EmpleadoDTO> {

    private final EmpleadoRepository repository;
    private final PersonaService personaService;
    private final ModelMapper modelMapper;
    private final UsuarioService usuarioService;
    private final ContactoService contactoService;

    public EmpleadoService(EmpleadoRepository repository, PersonaService personaService, ModelMapper modelMapper,
            UsuarioService usuarioService, ContactoService contactoService) {
        super(repository);
        this.repository = repository;
        this.personaService = personaService;
        this.modelMapper = modelMapper;
        this.usuarioService = usuarioService;
        this.contactoService = contactoService;
    }

    @Override
    public void validate(Empleado entity) {
        personaService.validate(entity);

        var legajo = entity.getLegajo();
        if (legajo == null || legajo.isBlank()) {
            throw new ServiceException("legajo required");
        }

        var existing = repository.findByLegajo(legajo);
        if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
            throw new ServiceException("legajo must be unique");
        }

        var tipoEmpleado = entity.getTipoEmpleado();
        if (tipoEmpleado == null) {
            throw new ServiceException("tipoEmpleado required");
        }
    }

    @Override
    protected Empleado toEntity(EmpleadoDTO dto) {
        return modelMapper.map(dto, Empleado.class);
    }

    @Override
    protected Empleado toEntity(UUID id, EmpleadoDTO dto) {
        var entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Empleado not found"));
        modelMapper.map(dto, entity);
        return entity;
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
