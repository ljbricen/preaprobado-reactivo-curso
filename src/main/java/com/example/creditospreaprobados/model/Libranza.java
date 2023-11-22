package com.example.creditospreaprobados.model;

import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Mono;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.example.creditospreaprobados.exception.CumpleReglaException;
import com.example.creditospreaprobados.model.action.CupoDisponibleInterface;
import com.example.creditospreaprobados.model.action.MensajeCliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Table("libranzas")
@Builder
public class Libranza implements CupoDisponibleInterface, MensajeCliente {
    @Id
    private Long id;

    @Column("total_prestamo")
    private BigDecimal totalPrestamo;

    @Column("total_consumido")
    private BigDecimal totalConsumido;

    @Column("cedula")
    private String cedula;
    
    @Column("fecha_vencimiento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento;

    @Override
    public String getMensajeTextoPreaprobado() {
        return "¡Felicitaciones! Tenemos un Libranza para ti, tienes disponible: "
            .concat(this.getCupoDisponible().toString());
    }

    @Override
    public BigDecimal getCupoDisponible() {
        return this.totalPrestamo.subtract(this.totalConsumido);
    }

    public Mono<Libranza> validarReglas() {
        return Mono.just(this)
            .filter(libranza -> libranza.getCedula() != null && !libranza.getCedula().isEmpty())
            .switchIfEmpty(Mono.error(new CumpleReglaException("La cedula no puede estar vacia")))
            .filter(libranza -> libranza.getFechaVencimiento() != null)
            .switchIfEmpty(Mono.error(new CumpleReglaException("La fecha de vencimiento no puede estar vacia")))
            .filter(libranza -> libranza.getTotalPrestamo() != null)
            .switchIfEmpty(Mono.error(new CumpleReglaException("El total prestamo no puede estar vacio")))
            .filter(libranza -> libranza.getTotalConsumido() != null)
            .filter(libranza -> libranza.totalPrestamo.compareTo(BigDecimal.valueOf(100000L)) > 0)
            .switchIfEmpty(Mono.error(new CumpleReglaException("El total prestamo debe ser mayor a 1000000")));
    }

}