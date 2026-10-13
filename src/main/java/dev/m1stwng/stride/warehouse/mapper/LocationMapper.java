package dev.m1stwng.stride.warehouse.mapper;

import dev.m1stwng.stride.warehouse.dto.response.LocationResponse;
import dev.m1stwng.stride.warehouse.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationResponse toResponse(Location location);
}
