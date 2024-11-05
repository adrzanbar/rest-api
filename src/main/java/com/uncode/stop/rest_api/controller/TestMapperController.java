package com.uncode.stop.rest_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.dto.PersonaDTO;
import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.mapper.PersonaMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("test/mapper")
@RequiredArgsConstructor
public class TestMapperController {

    private final PersonaMapper personaMapper;

    @PostMapping
    public Persona toEntity(@Valid @RequestBody PersonaDTO dto) {
        return personaMapper.toEntity(dto);
    }

}
