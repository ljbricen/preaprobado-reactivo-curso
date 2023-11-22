package com.example.creditospreaprobados.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creditospreaprobados.dto.LibranzaDto;
import com.example.creditospreaprobados.model.Libranza;
import com.example.creditospreaprobados.service.LibranzaService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;    

@RestController
@RequestMapping("/libranzas")
@RequiredArgsConstructor
public class LibranzaController {

    private final Logger logger = LoggerFactory.getLogger(LibranzaController.class);

    private final LibranzaService libranzaService;

    @PostMapping
    public Mono<Libranza> crearLibranza(@RequestBody LibranzaDto libranzaDto) {
        return Mono.just(libranzaDto)
            .doOnNext(dto -> logger.info("Creando libranza: {}", dto))
            .flatMap(libranzaService::crearLibranza)
            .doOnNext(libranza -> logger.info("Libranza creada: {}", libranza))
            .doOnError(error ->logger.error("Error creando libranza: {}", error.getMessage()));
    }

    @GetMapping
    public Flux<Libranza> obtenerLibranzas() {
        return libranzaService.obtenerLibranzas();
    }

    @GetMapping("/{id}")
    public Mono<Libranza> obtenerLibranzaPorId(@PathVariable Long id) {
        return libranzaService.obtenerLibranza(id);
    }

    @PutMapping("/{id}")
    public Mono<Libranza> actualizarLibranza(@PathVariable Long id, @RequestBody LibranzaDto libranzaDto) {
        return Mono.just(libranzaDto)
            .doOnNext(dto -> logger.info("Actualizando libranza: {}", dto))
            .flatMap(dto -> libranzaService.actualizarLibranza(id, dto))
            .doOnNext(libranza -> logger.info("Libranza actualizada: {}", libranza))
            .doOnError(error ->logger.error("Error actualizando libranza: {}", error.getMessage()));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminarLibranza(@PathVariable Long id) {
        return libranzaService.eliminarLibranza(id);
    }


    
}
