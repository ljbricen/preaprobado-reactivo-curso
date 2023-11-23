package com.example.creditospreaprobados.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.creditospreaprobados.dto.LibranzaDto;
import com.example.creditospreaprobados.exception.NoEncontradoException;
import com.example.creditospreaprobados.model.Libranza;
import com.example.creditospreaprobados.service.LibranzaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class LibranzaControllerTest {

    private LibranzaController libranzaController;

    @Mock
    private LibranzaService libranzaService;

    @BeforeEach
    public void setUp() {
        libranzaController = new LibranzaController(libranzaService);
    }

    @Test
    void testObtenerLibranzas() {
        // Arrange
        String cedula = "1234";
        Libranza libranza = Libranza.builder()
            .cedula(cedula)
            .build();
        when(libranzaService.obtenerLibranzas()).thenReturn(Flux.just(libranza));

        // Act & Assert
        StepVerifier.create(libranzaController.obtenerLibranzas())
            .expectNextMatches(libranza1 -> {
                assert libranza1.getCedula().equals(cedula);
                return true;
            })
            .verifyComplete();

    }

    @Test
    void testObtenerLibranzaEspecifico() {
        // Arrange
        String cedula = "1234";
        Libranza libranza = Libranza.builder()
            .cedula(cedula)
            .build();
        Long id = 2L;
        when(libranzaService.obtenerLibranza(id)).thenReturn(Mono.just(libranza));

        // Act & Assert
        StepVerifier.create(libranzaController.obtenerLibranzaPorId(id))
            .expectNextMatches(libranza1 -> {
                assert libranza1.getCedula().equals(cedula);
                return true;
            })
            .verifyComplete();

    }

    @Test
    void testObtenerLibranzaEspecificoNoEncontradpException() {
        // Arrange
        Long id = 2L;
        when(libranzaService.obtenerLibranza(id)).thenReturn(Mono.error(new NoEncontradoException("No encontrado")));

        // Act & Assert
        StepVerifier.create(libranzaController.obtenerLibranzaPorId(id))
            .expectErrorMatches(throwable -> throwable instanceof NoEncontradoException)
            .verify();

    }

    @Test
    void testCrearLibranza() {
        // Arrange
        String cedula = "1234";
        Long id = 2L;
        Libranza libranza = Libranza.builder()
            .id(id)
            .cedula(cedula)
            .build();
        LibranzaDto libranzaDto = new LibranzaDto(cedula, null, null, null);
        when(libranzaService.crearLibranza(libranzaDto)).thenReturn(Mono.just(libranza));

        // Act & Assert
        StepVerifier.create(libranzaController.crearLibranza(libranzaDto))
            .expectNextMatches(libranza1 -> {
                assert libranza1.getId() == id;
                return true;
            })
            .verifyComplete();

    }

    @Test
    void testActualizarLibranza() {
        // Arrange
        String cedula = "1234";
        Long id = 2L;
        Libranza libranza = Libranza.builder()
            .id(id)
            .cedula(cedula)
            .build();
        LibranzaDto libranzaDto = new LibranzaDto(cedula, null, null, null);
        when(libranzaService.actualizarLibranza(id, libranzaDto)).thenReturn(Mono.just(libranza));

        // Act & Assert
        StepVerifier.create(libranzaController.actualizarLibranza(id, libranzaDto))
            .expectNextMatches(libranza1 -> {
                assert libranza1.getId() == id;
                return true;
            })
            .verifyComplete();

    }

    @Test
    void testEliminarLibranza() {
        // Arrange
        Long id = 2L;
        when(libranzaService.eliminarLibranza(id)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(libranzaController.eliminarLibranza(id))
            .verifyComplete();

    }
    
}
