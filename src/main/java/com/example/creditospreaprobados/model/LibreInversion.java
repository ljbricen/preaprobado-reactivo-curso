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
@Table("libre_inversiones")
@Builder
public class LibreInversion implements CupoDisponibleInterface, MensajeClienteInterface {

        @Id
        private Long id;

        @Column("cedula")
        private String cedula;

        @Column("fecha_vencimiento")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate fechaVencimiento;

        @Column("total_prestamo")
        private BigDecimal totalPrestamo;

        @Column("consumido")
        private BigDecimal consumido;

        @Override
        public String getMensajeTextoPreaprobado() {
                return "¡Es tu día! Tenemos un Libre Inversión para ti, vence el: "
                                .concat(this.fechaVencimiento.toString());
        }

        @Override
        public BigDecimal getCupoDisponible() {
                return this.totalPrestamo.subtract(this.consumido);
        }

}
