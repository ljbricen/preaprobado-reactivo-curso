package com.example.creditospreaprobados.repository;

import com.example.creditospreaprobados.model.Libranza;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibranzaRepository extends R2dbcRepository<Libranza, Long> {
}
