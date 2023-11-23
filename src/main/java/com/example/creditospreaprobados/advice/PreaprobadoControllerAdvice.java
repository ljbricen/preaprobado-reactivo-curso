package com.example.creditospreaprobados.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.example.creditospreaprobados.exception.CumpleReglaException;
import com.example.creditospreaprobados.exception.NoEncontradoException;
import com.example.creditospreaprobados.exception.NoProcesadoException;

@RestControllerAdvice
public class PreaprobadoControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (ex instanceof DuplicateKeyException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Elemento con cedula ya existente");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CumpleReglaException.class)
    public ResponseEntity<String> handleCumpleReglaException(CumpleReglaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoEncontradoException.class)
    public ResponseEntity<String> handleNoEncontradoException(NoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoProcesadoException.class)
    public ResponseEntity<String> handleNoProcesadoException(NoProcesadoException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
    
}
