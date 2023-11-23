package com.example.creditospreaprobados.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.example.creditospreaprobados.model.action.CupoDisponibleInterface;
import com.example.creditospreaprobados.model.action.MensajeClienteInterface;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Table("tarjetas_creditos")
@Builder
public class TarjetaCredito implements CupoDisponibleInterface, MensajeClienteInterface {

    @Id
    private Long id;

    @Column("cedula")
    private String cedula;

    @Column("cupo")
    private BigDecimal cupo;

    @Column("consumido")
    private BigDecimal consumido;

    @Column("fecha_vencimiento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento;

    @Override
    public String getMensajeTextoPreaprobado() {
        return "¡Felicitaciones! Tenemos una Tarjeta de Crédito para ti, vence el: "
            .concat(this.fechaVencimiento.toString())
            .concat(" y tienes disponible: ").concat(this.getCupoDisponible().toString());
    }

    @Override
    public BigDecimal getCupoDisponible() {
        return this.cupo.subtract(this.consumido);
    }

    
}
