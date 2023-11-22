package com.example.creditospreaprobados.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creditospreaprobados.model.TarjetaCredito;
import com.example.creditospreaprobados.service.TarjetaCreditoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tarjetacreditos")
@RequiredArgsConstructor
public class TarjetaCreditoController {
    
    private final Logger logger = LoggerFactory.getLogger(TarjetaCreditoController.class);

    private final TarjetaCreditoService tarjetacreditoService;

    @GetMapping
    public Flux<TarjetaCredito> obtenerTarjetaCreditos() {
        return tarjetacreditoService.obtenerTarjetaCreditos();
    }

    @GetMapping("/{id}")
    public Mono<TarjetaCredito> obtenerTarjetaCreditoPorId(@PathVariable Long id) {
        return tarjetacreditoService.obtenerTarjetaCredito(id);
    }

}
