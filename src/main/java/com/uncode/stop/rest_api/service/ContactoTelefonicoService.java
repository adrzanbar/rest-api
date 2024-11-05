package com.uncode.stop.rest_api.service;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.ContactoTelefonico;
import com.uncode.stop.rest_api.repository.ContactoTelefonicoRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ContactoTelefonicoService extends ContactoService<ContactoTelefonico> {

    private final ContactoTelefonicoRepository repository;


}
