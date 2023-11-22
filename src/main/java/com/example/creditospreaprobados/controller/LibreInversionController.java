package com.example.creditospreaprobados.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creditospreaprobados.model.LibreInversion;
import com.example.creditospreaprobados.service.LibreInversionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/libreinversion")
@RequiredArgsConstructor
public class LibreInversionController {

    private final Logger logger = LoggerFactory.getLogger(LibreInversion.class);

    private final LibreInversionService libreinversionService;

    @GetMapping
    public Flux<LibreInversion> obtenerLibreInversiones() {
        return libreinversionService.obtenerLibreInversions();
    }

    @GetMapping("/{id}")
    public Mono<LibreInversion> obtenerLibreInversionPorId(@PathVariable Long id) {
        return libreinversionService.obtenerLibreInversion(id);
    }
    
}
