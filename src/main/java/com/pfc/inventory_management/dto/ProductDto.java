package com.pfc.inventory_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDto(
        @NotNull String name,
        @NotNull String description,
        @NotNull @Min(0) BigDecimal price,
        @NotNull @Min(0) int quantity,
        @NotNull @Min(0) Long categoryId,
        @NotNull @Min(0) Long supplierId
) {
}
