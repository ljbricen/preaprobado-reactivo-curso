package com.example.creditospreaprobados.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.example.creditospreaprobados.exception.NoEncontradoException;
import com.example.creditospreaprobados.model.Crediagil;
import com.example.creditospreaprobados.repository.CrediagilRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CrediagilService {

    private final Logger logger = LoggerFactory.getLogger(CrediagilService.class);

    private final CrediagilRepository crediagilRepository;

    public Flux<Crediagil> obtenerCrediagils() {
        return crediagilRepository.findAll()
            .onErrorResume(error -> {
                logger.error(error.getMessage());
                return Mono.empty();
            });
    }

    public Mono<Crediagil> obtenerCrediagil(Long id) {
        return crediagilRepository.findById(id)
            .onErrorResume(error -> {
                logger.error(error.getMessage());
                return Mono.error(new NoEncontradoException("Crediagil no encontrado"));
            })
            .switchIfEmpty(Mono.error(new NoEncontradoException("Crediagil no encontrado")));
    }
    
}
