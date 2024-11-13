package com.uncode.stop.rest_api.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.SoftDelete;


import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@SoftDelete(columnName = "eliminado")
@Getter
@Setter
public class UnidadDeNegocio implements Identifiable<UUID>{
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String nombre;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Direccion direccion;
    
    @OneToMany(fetch = FetchType.EAGER)
    private List<Servicio> servicio;

}
