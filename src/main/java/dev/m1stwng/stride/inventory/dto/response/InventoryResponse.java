package dev.m1stwng.stride.inventory.dto.response;

import java.util.UUID;

public record InventoryResponse(UUID id, UUID productId, UUID locationId, int quantity) {
}
