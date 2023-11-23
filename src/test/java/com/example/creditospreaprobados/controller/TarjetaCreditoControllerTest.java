package com.example.creditospreaprobados.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.creditospreaprobados.exception.NoEncontradoException;
import com.example.creditospreaprobados.model.TarjetaCredito;
import com.example.creditospreaprobados.service.TarjetaCreditoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class TarjetaCreditoControllerTest {

    private TarjetaCreditoController tarjetaCreditoController;

    @Mock
    private TarjetaCreditoService tarjetaCreditoService;

    @BeforeEach
    public void setUp() {
        tarjetaCreditoController = new TarjetaCreditoController(tarjetaCreditoService);
    }

    @Test
    void testObtenerTarjetaCreditos() {
        // Arrange
        String cedula = "1234";
        TarjetaCredito tarjetaCredito = TarjetaCredito.builder()
            .cedula(cedula)
            .build();
        when(tarjetaCreditoService.obtenerTarjetaCreditos()).thenReturn(Flux.just(tarjetaCredito));

        // Act & Assert
        StepVerifier.create(tarjetaCreditoController.obtenerTarjetaCreditos())
            .expectNextMatches(tarjetaCredito1 -> {
                assert tarjetaCredito1.getCedula().equals(cedula);
                return true;
            })
            .verifyComplete();

    }

    @Test
    void testObtenerTarjetaCreditoEspecifico() {
        // Arrange
        String cedula = "1234";
        TarjetaCredito tarjetaCredito = TarjetaCredito.builder()
            .cedula(cedula)
            .build();
        Long id = 2L;
        when(tarjetaCreditoService.obtenerTarjetaCredito(id)).thenReturn(Mono.just(tarjetaCredito));

        // Act & Assert
        StepVerifier.create(tarjetaCreditoController.obtenerTarjetaCreditoPorId(id))
            .expectNextMatches(tarjetaCredito1 -> {
                assert tarjetaCredito1.getCedula().equals(cedula);
                return true;
            })
            .verifyComplete();

    }

    @Test
    void testObtenerTarjetaCreditoNoEncontrado(){
        // Arrange
        Long id = 2L;
        when(tarjetaCreditoService.obtenerTarjetaCredito(id))
            .thenReturn(Mono.error(new NoEncontradoException("TarjetaCredito no encontrado")));

        // Act & Assert
        StepVerifier.create(tarjetaCreditoController.obtenerTarjetaCreditoPorId(id))
            .expectErrorMatches(throwable -> throwable instanceof NoEncontradoException)
            .verify();
    }
    
}
