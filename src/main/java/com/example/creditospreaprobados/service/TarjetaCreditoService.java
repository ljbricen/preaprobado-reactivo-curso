package com.example.creditospreaprobados.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.creditospreaprobados.exception.NoEncontradoException;
import com.example.creditospreaprobados.model.TarjetaCredito;
import com.example.creditospreaprobados.repository.TarjetaCreditoRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TarjetaCreditoService {

    private final Logger logger = LoggerFactory.getLogger(TarjetaCreditoService.class);

    private final TarjetaCreditoRepository tarjetaCreditoRepository;

    public Flux<TarjetaCredito> obtenerTarjetaCreditos() {
        return tarjetaCreditoRepository.findAll();
    }

    public Mono<TarjetaCredito> obtenerTarjetaCredito(Long id) {
        return tarjetaCreditoRepository.findById(id)
            .switchIfEmpty(Mono.error(new NoEncontradoException("TarjetaCredito no encontrado")));
    }
    
}
