package com.example.creditospreaprobados.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TarjetaCreditoDto(
    String cedula,
    BigDecimal cupo,
    BigDecimal consumido,
    LocalDate fechaVencimiento
) {
    
}
