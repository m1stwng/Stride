package dev.m1stwng.stride.warehouse.service;

import dev.m1stwng.stride.warehouse.dto.request.CreateWarehouseRequest;
import dev.m1stwng.stride.warehouse.dto.request.UpdateWarehouseRequest;
import dev.m1stwng.stride.warehouse.dto.response.WarehouseResponse;
import dev.m1stwng.stride.warehouse.entity.Warehouse;
import dev.m1stwng.stride.warehouse.exception.DuplicateWarehouseException;
import dev.m1stwng.stride.warehouse.exception.WarehouseNotFoundException;
import dev.m1stwng.stride.warehouse.mapper.WarehouseMapper;
import dev.m1stwng.stride.warehouse.repository.WarehouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseMapper warehouseMapper;
    private final WarehouseRepository warehouseRepository;

    public List<WarehouseResponse> findAll() {
        return warehouseRepository.findAll()
                .stream()
                .map(warehouseMapper::toResponse)
                .toList();
    }

    public WarehouseResponse findById(UUID id) {
        return warehouseRepository.findById(id)
                .map(warehouseMapper::toResponse)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
    }

    public WarehouseResponse create(CreateWarehouseRequest request) {
        final String code = request.code().trim().toUpperCase();

        if (warehouseRepository.existsByCode(code)) {
            throw new DuplicateWarehouseException(code);
        }

        final Warehouse warehouse = Warehouse.builder()
                .code(code)
                .alias(request.alias())
                .build();

        return warehouseMapper.toResponse(warehouseRepository.save(warehouse));
    }

    public WarehouseResponse update(UUID id, UpdateWarehouseRequest request) {
        final Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));

        final String code = request.code().trim().toUpperCase();

        if (warehouseRepository.existsByCodeAndIdNot(code, id)) {
            throw new DuplicateWarehouseException(code);
        }

        warehouse.setCode(code);
        warehouse.setAlias(request.alias());

        return warehouseMapper.toResponse(warehouseRepository.save(warehouse));
    }

    public void delete(UUID id) {
        if (!warehouseRepository.existsById(id)) {
            throw new WarehouseNotFoundException(id);
        }

        warehouseRepository.deleteById(id);
    }
}
