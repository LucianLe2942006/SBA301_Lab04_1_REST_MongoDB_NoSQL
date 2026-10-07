package com.example.lab41.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record ProductRequest(
    @NotBlank String name,
    @NotNull @PositiveOrZero BigDecimal price,
    @NotNull @PositiveOrZero Integer stock,
    String categoryId
) {
}
