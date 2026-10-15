package dev.m1stwng.stride.procurement.supplier.dto.response;

import java.util.UUID;

public record SupplierResponse(UUID id, String name, String email, String phone) {
}
