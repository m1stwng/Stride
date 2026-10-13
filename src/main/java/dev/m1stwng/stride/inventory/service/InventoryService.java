package dev.m1stwng.stride.inventory.service;

import dev.m1stwng.stride.inventory.dto.response.InventoryResponse;
import dev.m1stwng.stride.inventory.mapper.InventoryMapper;
import dev.m1stwng.stride.inventory.repository.InventoryRepository;
import dev.m1stwng.stride.product.exception.ProductNotFoundException;
import dev.m1stwng.stride.product.repository.ProductRepository;
import dev.m1stwng.stride.warehouse.exception.LocationNotFoundException;
import dev.m1stwng.stride.warehouse.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryMapper inventoryMapper;
    private final InventoryRepository inventoryRepository;
    private final LocationRepository locationRepository;
    private final ProductRepository productRepository;

    public Page<InventoryResponse> findAllByProductId(UUID productId, Pageable pageable) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }

        return inventoryRepository.findAllByProductId(productId, pageable)
                .map(inventoryMapper::toResponse);
    }

    public Page<InventoryResponse> findAllByLocationId(UUID locationId, Pageable pageable) {
        if (!locationRepository.existsById(locationId)) {
            throw new LocationNotFoundException(locationId);
        }

        return inventoryRepository.findAllByLocationId(locationId, pageable)
                .map(inventoryMapper::toResponse);
    }
}
