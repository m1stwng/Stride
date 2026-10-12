package dev.m1stwng.stride.product.exception;

import dev.m1stwng.stride.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicateProductException extends BaseException {
    public DuplicateProductException(String sku) {
        super(HttpStatus.CONFLICT, "Duplicate product sku", "Product with sku %s already exists".formatted(sku));
    }
}
