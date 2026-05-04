package com.pfc.inventory_management.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryDto(@NotNull String name) {
}
