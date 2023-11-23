package com.example.creditospreaprobados.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.creditospreaprobados.exception.NoEncontradoException;
import com.example.creditospreaprobados.model.LibreInversion;
import com.example.creditospreaprobados.service.LibreInversionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class LibreInversionControllerTest {

    private LibreInversionController libreInversionController;

    @Mock
    private LibreInversionService libreInversionService;

    @BeforeEach
    public void setUp() {
        libreInversionController = new LibreInversionController(libreInversionService);
    }

    @Test
    void testObtenerLibreInversiones() {
        // Arrange
        String cedula = "1234";
        LibreInversion libreInversion = LibreInversion.builder()
            .cedula(cedula)
            .build();
        when(libreInversionService.obtenerLibreInversions()).thenReturn(Flux.just(libreInversion));

        // Act & Assert
        StepVerifier.create(libreInversionController.obtenerLibreInversiones())
            .expectNextMatches(libreInversion1 -> {
                assert libreInversion1.getCedula().equals(cedula);
                return true;
            })
            .verifyComplete();

    }

    @Test
    void testObtenerLibreInversionEspecifico() {
        // Arrange
        String cedula = "1234";
        LibreInversion libreInversion = LibreInversion.builder()
            .cedula(cedula)
            .build();
        Long id = 2L;
        when(libreInversionService.obtenerLibreInversion(id)).thenReturn(Mono.just(libreInversion));

        // Act & Assert
        StepVerifier.create(libreInversionController.obtenerLibreInversionPorId(id))
            .expectNextMatches(libreInversion1 -> {
                assert libreInversion1.getCedula().equals(cedula);
                return true;
            })
            .verifyComplete();

    }

    @Test
    void testObtenerLibreInversionNoEncontrado() {
        // Arrange
        Long id = 2L;
        when(libreInversionService.obtenerLibreInversion(id))
            .thenReturn(Mono.error(new NoEncontradoException("LibreInversion no encontrado")));

        // Act & Assert
        StepVerifier.create(libreInversionController.obtenerLibreInversionPorId(id))
            .expectError(NoEncontradoException.class)
            .verify();
    }
    
}
