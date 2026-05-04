package com.pfc.inventory_management.dto;

import jakarta.validation.constraints.NotNull;

public record SupplierDto(@NotNull String name) {
}
