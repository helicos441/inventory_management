package com.pfc.inventory_management.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record SearchProductPricesDto(
        @NotNull @Positive BigDecimal minPrice,
        @NotNull @Positive BigDecimal maxPrice
) {
}
