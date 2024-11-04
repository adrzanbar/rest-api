package com.uncode.stop.rest_api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uncode.stop.rest_api.error.NotFoundException;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.mapper.DtoMapper;
import com.uncode.stop.rest_api.service.CrudService;
import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.validation.Valid;

public abstract class CrudController<E extends Identifiable<ID>, ID, DTO> {

    protected abstract CrudService<E, ID> getService();

    protected abstract DtoMapper<E, DTO> getMapper();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DTO create(@Valid @RequestBody DTO dto) {
        return getMapper().toDto(getService().create(getMapper().toEntity(dto)));
    }

    @GetMapping("/{id}")
    public DTO readOne(@PathVariable ID id) {
        return getMapper().toDto(getService().readOne(id));
    }

    @GetMapping
    public Page<DTO> read(Pageable pageable) {
        return getService().readAll(pageable).map(getMapper()::toDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DTO update(@PathVariable ID id, @Valid @RequestBody DTO dto) {
        E entity = getMapper().toEntity(dto);
        entity.setId(id);
        return getMapper().toDto(getService().update(entity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable ID id) {
        getService().delete(id);
        return "Deleted";
    }

}
