package com.example.creditospreaprobados.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LibreInversionDto(
    String cedula,
    LocalDate fechaVencimiento,
    BigDecimal totalPrestamo,
    BigDecimal consumido
) {
    
}
