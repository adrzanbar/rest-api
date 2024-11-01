package com.uncode.stop.rest_api.mapper;

public interface DtoMapper<E, DTO> {

    DTO toDto(E entity);

    E toEntity(DTO dto);

}
