package com.example.creditospreaprobados.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.creditospreaprobados.dto.LibranzaDto;
import com.example.creditospreaprobados.exception.NoEncontradoException;
import com.example.creditospreaprobados.exception.NoProcesadoException;
import com.example.creditospreaprobados.model.Libranza;
import com.example.creditospreaprobados.repository.LibranzaRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LibranzaService {

    private static final Logger logger = LoggerFactory.getLogger(LibranzaService.class);

    private final LibranzaRepository libranzaRepository;

    public Mono<Libranza> crearLibranza(LibranzaDto libranzaDto) {
        return Mono.just(Libranza.builder()
                .cedula(libranzaDto.cedula())
                .totalPrestamo(libranzaDto.totalPrestamo())
                .totalConsumido(libranzaDto.totalConsumido())
                .fechaVencimiento(libranzaDto.fechaVencimiento())
                .build()
            )
            .doOnNext(libranza -> logger.info("Creando libranza: {}", libranza))
            .flatMap(Libranza::validarReglas)
            .flatMap(libranzaRepository::save)
            .onErrorResume(error -> {
                logger.error(error.getMessage());
                return Mono.error(new NoEncontradoException("Libranza no encontrada"));
            });
    }

    public Flux<Libranza> obtenerLibranzas() {
        return libranzaRepository.findAll()
            .onErrorResume(error -> {
                logger.error(error.getMessage());
                return Mono.empty();
            });
    }

    public Mono<Libranza> obtenerLibranza(Long id) {
        return libranzaRepository.findById(id)
            .onErrorResume(error -> {
                logger.error(error.getMessage());
                return Mono.error(new NoEncontradoException("Libranza no encontrada"));
            })
            .switchIfEmpty(Mono.error(new NoEncontradoException("Libranza no encontrada")));
    }

    
    public Mono<Libranza> actualizarLibranza(Long id, LibranzaDto libranzaDto) {
        return obtenerLibranza(id)
            .doOnNext(libranza -> logger.info("Se encontró libranza: {}", libranza))
            .map(libranza -> Libranza.builder()
                .id(libranza.getId())
                .cedula(libranzaDto.cedula())
                .totalPrestamo(libranzaDto.totalPrestamo())
                .totalConsumido(libranzaDto.totalConsumido())
                .fechaVencimiento(libranzaDto.fechaVencimiento())
                .build())
            .doOnNext(libranza -> logger.info("Se modifica libranza: {}", libranza))
            .flatMap(Libranza::validarReglas)
            .flatMap(libranzaRepository::save)
            .onErrorResume(error -> {
                logger.error(error.getMessage());
                return Mono.error(new NoProcesadoException("Libranza no procesada"));
            });
    }

    public Mono<Void> eliminarLibranza(Long id) {
        return obtenerLibranza(id)
            .doOnNext(libranza -> logger.info("Se encontró libranza: {}", libranza))
            .flatMap(libranzaRepository::delete)
            .onErrorResume(error -> {
                logger.error(error.getMessage());
                return Mono.error(new NoProcesadoException("Libranza no procesada"));
            });
    }
    
    
}
