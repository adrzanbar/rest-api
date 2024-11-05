package com.uncode.stop.rest_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.uncode.stop.rest_api.entity.Contacto;

@NoRepositoryBean
public interface ContactoRepository<T extends Contacto> extends JpaRepository<T, UUID> {

}
