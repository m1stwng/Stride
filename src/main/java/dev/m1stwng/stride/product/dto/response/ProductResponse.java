package dev.m1stwng.stride.product.dto.response;

import java.util.UUID;

public record ProductResponse(UUID id, String sku, String name) {
}
