package dev.m1stwng.stride.warehouse.controller;

import dev.m1stwng.stride.inventory.dto.response.InventoryResponse;
import dev.m1stwng.stride.inventory.service.InventoryService;
import dev.m1stwng.stride.warehouse.dto.response.LocationResponse;
import dev.m1stwng.stride.warehouse.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final InventoryService inventoryService;
    private final LocationService locationService;

    @GetMapping("/{id}/inventories")
    ResponseEntity<Page<InventoryResponse>> findAllInventoriesByLocationId(@PathVariable UUID id, Pageable pageable) {
        return ResponseEntity.ok(inventoryService.findAllByLocationId(id, pageable));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<LocationResponse> delete(@PathVariable UUID id) {
        locationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
