package com.uncode.stop.rest_api.mapper;

import org.springframework.stereotype.Component;

@Component 
public class IdentityMapper<T> implements DtoMapper<T, T> {

    @Override
    public T toDto(T entity) {
        return entity;
    }

    @Override
    public T toEntity(T dto) {
        return dto;
    }

}
