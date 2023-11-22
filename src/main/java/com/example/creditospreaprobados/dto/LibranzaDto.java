package com.example.creditospreaprobados.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LibranzaDto(
    String cedula,
    BigDecimal totalPrestamo,
    BigDecimal totalConsumido,
    LocalDate fechaVencimiento
) {
}
