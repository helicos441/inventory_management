package com.pfc.inventory_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SearchProductPricesDto(
        @NotNull @Min(0) BigDecimal minPrice,
        @NotNull @Min(0) BigDecimal maxPrice
) {
}
