package dev.m1stwng.stride.procurement.supplier.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateSupplierRequest(

        @NotBlank
        String name,

        @Email
        String email,

        String phone
) {
}
