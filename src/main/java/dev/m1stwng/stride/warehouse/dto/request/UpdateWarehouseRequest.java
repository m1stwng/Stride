package dev.m1stwng.stride.warehouse.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateWarehouseRequest(

        @NotBlank
        String code,

        String alias
) {
}
