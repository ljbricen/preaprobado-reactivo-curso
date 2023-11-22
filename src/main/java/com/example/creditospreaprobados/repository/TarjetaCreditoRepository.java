package com.example.creditospreaprobados.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.example.creditospreaprobados.model.TarjetaCredito;

public interface TarjetaCreditoRepository extends R2dbcRepository<TarjetaCredito, Long>{
    
}
