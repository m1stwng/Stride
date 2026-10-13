package dev.m1stwng.stride.warehouse.service;

import dev.m1stwng.stride.warehouse.dto.request.CreateLocationRequest;
import dev.m1stwng.stride.warehouse.dto.response.LocationResponse;
import dev.m1stwng.stride.warehouse.entity.Location;
import dev.m1stwng.stride.warehouse.entity.Warehouse;
import dev.m1stwng.stride.warehouse.exception.DuplicateLocationException;
import dev.m1stwng.stride.warehouse.exception.LocationNotFoundException;
import dev.m1stwng.stride.warehouse.exception.WarehouseNotFoundException;
import dev.m1stwng.stride.warehouse.mapper.LocationMapper;
import dev.m1stwng.stride.warehouse.repository.LocationRepository;
import dev.m1stwng.stride.warehouse.repository.WarehouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {

    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;
    private final WarehouseRepository warehouseRepository;

    public List<LocationResponse> findAllByWarehouseId(UUID id) {
        if (!warehouseRepository.existsById(id)) {
            throw new WarehouseNotFoundException(id);
        }

        return locationRepository.findAllByWarehouseId(id)
                .stream()
                .map(locationMapper::toResponse)
                .toList();
    }

    public LocationResponse create(UUID warehouseId, CreateLocationRequest request) {
        final Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new WarehouseNotFoundException(warehouseId));

        final String code = request.code().trim().toUpperCase(Locale.ROOT);

        if (locationRepository.existsByCode(code)) {
            throw new DuplicateLocationException(code);
        }

        final Location location = Location.builder()
                .code(code)
                .warehouse(warehouse)
                .build();

        return locationMapper.toResponse(locationRepository.save(location));
    }

    public void delete(UUID id) {
        if (!locationRepository.existsById(id)) {
            throw new LocationNotFoundException(id);
        }

        locationRepository.deleteById(id);
    }
}
