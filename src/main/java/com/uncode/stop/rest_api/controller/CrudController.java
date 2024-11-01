package com.uncode.stop.rest_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.uncode.stop.rest_api.service.CrudService;

public abstract class CrudController<ID, DTO> {

    protected abstract CrudService<?, ID, DTO> getService();

    @PostMapping
    public DTO create(@RequestBody DTO dto) {
        return getService().create(dto);
    }

    @GetMapping("/{id}")
    public DTO read(@PathVariable ID id) {
        return getService().read(id);
    }

    @GetMapping
    public Page<DTO> read(Pageable pageable) {
        return getService().read(pageable);
    }

    @PutMapping("/{id}")
    public DTO update(@PathVariable ID id, @RequestBody DTO dto) {
        return getService().update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable ID id) {
        getService().delete(id);
        return "Deleted";
    }

}
