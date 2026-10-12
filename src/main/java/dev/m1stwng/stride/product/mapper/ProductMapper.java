package dev.m1stwng.stride.product.mapper;

import dev.m1stwng.stride.product.dto.response.ProductResponse;
import dev.m1stwng.stride.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);
}
