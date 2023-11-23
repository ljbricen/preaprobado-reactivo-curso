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
@Table("crediagiles")
@Builder
public class Crediagil implements CupoDisponibleInterface, MensajeClienteInterface {
    @Id
    private Long id;

    @Column("cedula")
    private String cedula;

    @Column("cupo")
    private BigDecimal cupo;

    @Column("total_consumido")
    private BigDecimal totalConsumido;

    @Column("fecha_vencimiento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento;

    @Override
    public String getMensajeTextoPreaprobado() {
        return "¡Felicitaciones! Tenemos un Crediágil para ti, vence el: ".concat(this.fechaVencimiento.toString());
    }

    @Override
    public BigDecimal getCupoDisponible() {
        return this.cupo.subtract(this.totalConsumido);
    }    
}
