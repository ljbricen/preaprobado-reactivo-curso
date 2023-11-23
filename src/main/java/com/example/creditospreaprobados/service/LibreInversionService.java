package com.example.creditospreaprobados.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.creditospreaprobados.exception.NoEncontradoException;
import com.example.creditospreaprobados.model.LibreInversion;
import com.example.creditospreaprobados.repository.LibreInversionRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class LibreInversionService {

    private final Logger logger = LoggerFactory.getLogger(LibreInversionService.class);

    private final LibreInversionRepository libreInversionRepository;

    public Flux<LibreInversion> obtenerLibreInversions() {
        return libreInversionRepository.findAll()
            .onErrorResume(error -> {
                logger.error(error.getMessage());
                return Mono.empty();
            });
    }

    public Mono<LibreInversion> obtenerLibreInversion(Long id) {
        return libreInversionRepository.findById(id)
            .onErrorResume(error -> {
                logger.error(error.getMessage());
                return Mono.error(new NoEncontradoException("LibreInversion no encontrado"));
            })
            .switchIfEmpty(Mono.error(new NoEncontradoException("LibreInversion no encontrado")));
    }
    
}
