package dev.m1stwng.stride.procurement.supplier.exception;

import dev.m1stwng.stride.common.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class SupplierNotFoundException extends BaseException {
    public SupplierNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, "Supplier was not found", "Supplier with id %s was not found".formatted(id));
    }
}
