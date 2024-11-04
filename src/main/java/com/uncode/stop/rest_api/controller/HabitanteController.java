package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.mapper.IdentityMapper;
import com.uncode.stop.rest_api.service.HabitanteService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping("/habitantes")
public class HabitanteController extends CrudController<Habitante, UUID, Habitante> {

    private final HabitanteService service;
    private final IdentityMapper<Habitante> mapper;

}

