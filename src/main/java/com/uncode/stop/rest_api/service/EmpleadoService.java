package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.EmpleadoDTO;
import com.uncode.stop.rest_api.dto.UsuarioDTO;
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

    public EmpleadoService(EmpleadoRepository repository, PersonaService personaService, ModelMapper modelMapper, UsuarioService usuarioService) {
        super(repository);
        this.repository = repository;
        this.personaService = personaService;
        this.modelMapper = modelMapper;
        this.usuarioService = usuarioService;
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

}
