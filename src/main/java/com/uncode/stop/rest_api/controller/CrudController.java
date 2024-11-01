package com.uncode.stop.rest_api.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.uncode.stop.rest_api.service.CrudService;

public abstract class CrudController<ID, DTO> {

    protected abstract CrudService<?, ID, DTO> getService();

    @PostMapping
    public DTO create(DTO dto) {
        return getService().create(dto);
    }

    @GetMapping("/{id}")
    public DTO read(@PathVariable ID id) {
        return getService().read(id);
    }

    @GetMapping
    public Set<DTO> read() {
        return getService().read();
    }

}
