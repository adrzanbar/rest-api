package com.uncode.stop.rest_api.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.error.NotFoundException;

import jakarta.transaction.Transactional;

public abstract class CrudService<E extends Identifiable<ID>, ID> {

    protected abstract JpaRepository<E, ID> getRepository();

    protected abstract void validate(E entity);

    @Transactional
    public E create(E entity) {
        entity.setId(null);
        validate(entity);
        return getRepository().save(entity);
    }

    public E readOne(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new NotFoundException("Entity not found"));
    }

    public E readOne(E entity) {
        return getRepository().findOne(Example.of(entity))
                .orElseThrow(() -> new NotFoundException("Entity not found"));
    }

    public Page<E> readMany(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public Page<E> readMany(E entity, Pageable pageable) {
        return getRepository().findAll(Example.of(entity), pageable);
    }

    public Page<E> readAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Transactional
    public E update(E entity) {
        validate(entity);
        if (getRepository().existsById(entity.getId())) {
            return getRepository().save(entity);
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
