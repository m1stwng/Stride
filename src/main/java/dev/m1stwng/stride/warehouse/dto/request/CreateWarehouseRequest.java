package dev.m1stwng.stride.warehouse.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateWarehouseRequest(

        @NotBlank
        String code,

        String alias
) {
}
