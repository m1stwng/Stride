package dev.m1stwng.stride.product.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateProductRequest(

        @NotBlank
        String sku,

        @NotBlank
        String name
) {
}
