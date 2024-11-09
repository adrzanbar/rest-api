package com.uncode.stop.rest_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.error.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrudService<E extends Identifiable<ID>, ID, DTO> implements Validator<E> {

    protected final JpaRepository<E, ID> repository;

    public abstract E toEntity(DTO dto);

    public abstract DTO toDTO(E entity);

    @Transactional
    public DTO create(DTO dto) {
        var entity = toEntity(dto);
        entity.setId(null);
        validate(entity);
        return toDTO(repository.save(entity));
    }

    public DTO readOne(ID id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entity not found")));
    }

    public Page<DTO> readAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toDTO);
    }

    @Transactional
    public DTO update(DTO dto) {
        var entity = toEntity(dto);
        validate(entity);
        if (repository.existsById(entity.getId())) {
            return toDTO(repository.save(entity));
        } else {
            throw new NotFoundException("Entity not found");
        }
    }

    @Transactional
    public void delete(ID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("Entity not found");
        }
    }

}
