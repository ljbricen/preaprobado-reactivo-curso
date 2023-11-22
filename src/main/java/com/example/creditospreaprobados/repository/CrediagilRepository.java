package com.example.creditospreaprobados.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.example.creditospreaprobados.model.Crediagil;

@Repository
public interface CrediagilRepository extends R2dbcRepository<Crediagil, Long>{
    
}
