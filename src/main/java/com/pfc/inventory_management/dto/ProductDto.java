package com.pfc.inventory_management.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDto(
        @NotNull String name,
        @NotNull String description,
        @NotNull @Positive BigDecimal price,
        @NotNull @Positive int quantity,
        @NotNull @Positive Long categoryId,
        @NotNull @Positive Long supplierId
) {
}
