package com.uncode.stop.rest_api.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.error.NotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

public abstract class CrudService<E extends Identifiable<ID>, ID, DTO> {

    protected abstract JpaRepository<E, ID> getRepository();

    protected abstract DTO toDto(E entity);

    protected abstract E toEntity(DTO dto);

    protected abstract void validate(E entity);

    @Transactional
    public DTO create(@Valid DTO dto) {
        E entity = toEntity(dto);
        validate(entity);
        entity.setId(null);
        return toDto(getRepository().save(entity));
    }

    public DTO read(ID id) {
        return toDto(getRepository().findById(id).orElseThrow(() -> new NotFoundException("Entity not found")));
    }

    public Set<DTO> read() {
        return getRepository().findAll().stream().map(this::toDto).collect(Collectors.toSet());
    }

    @Transactional
    public DTO update(@Valid DTO dto) {
        E entity = toEntity(dto);
        validate(entity);
        if (getRepository().existsById(entity.getId())) {
            return toDto(getRepository().save(entity));
        } else {
            throw new NotFoundException("Entity not found");
        }
    }

    @Transactional
    public void delete(ID id) {
        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
        } else {
            throw new NotFoundException("Entity not found");
        }
    }

}
