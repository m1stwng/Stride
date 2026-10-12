package dev.m1stwng.stride.product.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateProductRequest(

        @NotBlank
        String sku,

        @NotBlank
        String name
) {
}
