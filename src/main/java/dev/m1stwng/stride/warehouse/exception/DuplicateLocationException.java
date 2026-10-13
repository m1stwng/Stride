package dev.m1stwng.stride.warehouse.exception;

import dev.m1stwng.stride.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicateLocationException extends BaseException {
    public DuplicateLocationException(String code) {
        super(HttpStatus.CONFLICT, "Duplicate location code", "Location with code %s already exists".formatted(code));
    }
}
