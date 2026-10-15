package dev.m1stwng.stride.procurement.supplier.mapper;

import dev.m1stwng.stride.procurement.supplier.dto.response.SupplierResponse;
import dev.m1stwng.stride.procurement.supplier.entity.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierResponse toResponse(Supplier supplier);
}
