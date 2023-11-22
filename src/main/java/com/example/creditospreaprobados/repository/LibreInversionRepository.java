package com.example.creditospreaprobados.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.example.creditospreaprobados.model.LibreInversion;

public interface LibreInversionRepository extends R2dbcRepository<LibreInversion, Long> {
    
}
