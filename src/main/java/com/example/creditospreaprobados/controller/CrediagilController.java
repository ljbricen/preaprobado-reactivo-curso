package com.example.creditospreaprobados.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creditospreaprobados.model.Crediagil;
import com.example.creditospreaprobados.service.CrediagilService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/crediagiles")
@RequiredArgsConstructor
public class CrediagilController {

    private final Logger logger = LoggerFactory.getLogger(CrediagilController.class);

    private final CrediagilService crediagilService;

    @GetMapping
    public Flux<Crediagil> obtenerCrediagiles() {
        return crediagilService.obtenerCrediagils();
    }

    @GetMapping("/{id}")
    public Mono<Crediagil> obtenerCrediagilPorId(@PathVariable Long id) {
        return crediagilService.obtenerCrediagil(id);
    }
    
    
}
