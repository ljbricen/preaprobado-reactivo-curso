package com.example.creditospreaprobados.controller;

import com.example.creditospreaprobados.exception.NoEncontradoException;
import com.example.creditospreaprobados.model.Crediagil;
import com.example.creditospreaprobados.service.CrediagilService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrediagilControllerTest {

    @Mock
    private CrediagilService crediagilService;

    private CrediagilController crediagilController;

    @BeforeEach
    public void setUp() {
        crediagilController = new CrediagilController(crediagilService);
    }

    @Test
    void testObtenerCrediagiles() {
        // Arrange
        String cedula = "1234";
        Crediagil crediagil = Crediagil.builder().cedula(cedula).build();
        when(crediagilService.obtenerCrediagils()).thenReturn(Flux.just(crediagil));

        // Act
        Flux<Crediagil> crediagilResult = crediagilController.obtenerCrediagiles();

        // Assert
        StepVerifier.create(crediagilResult)
                        .expectNextMatches(crediagil1 -> {
                            assert crediagil1.getCedula().equals(cedula);
                            return true;
                        })
                        .verifyComplete();

    }

    @Test
    void testObtenerCrediagilEspecifico() {
        // Arrange
        Long id = 59L;
        String cedula = "1234";
        Crediagil crediagil = Crediagil.builder().cedula(cedula).build();
        when(crediagilService.obtenerCrediagil(id)).thenReturn(Mono.just(crediagil));

        // Act
        Mono<Crediagil> crediagilResult = crediagilController.obtenerCrediagilPorId(id);

        // Assert
        StepVerifier.create(crediagilResult)
                .expectNextMatches(crediagil1 -> {
                    assert crediagil1.getCedula().equals(cedula);
                    return true;
                })
                .verifyComplete();

    }

    @Test
    void testObtenerCrediagilEspecificoNoEncontrado() {
        // Arrange
        Long id = 59L;
        when(crediagilService.obtenerCrediagil(id)).thenReturn(Mono.error(new NoEncontradoException("Crediagil no encontrado")));

        // Act
        var crediagilResult = crediagilController.obtenerCrediagilPorId(id);

        // Assert
        StepVerifier.create(crediagilResult)
            .expectErrorMatches(throwable -> throwable instanceof NoEncontradoException)
            .verify();
    }



}