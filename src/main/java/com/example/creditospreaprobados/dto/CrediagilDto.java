package com.example.creditospreaprobados.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CrediagilDto(
    String cedula,
    BigDecimal cupo,
    BigDecimal totalConsumido,
    LocalDate fechaVencimiento
) {
    
}
