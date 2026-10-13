package dev.m1stwng.stride.warehouse.controller;

import dev.m1stwng.stride.warehouse.dto.response.LocationResponse;
import dev.m1stwng.stride.warehouse.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @DeleteMapping("/{id}")
    ResponseEntity<LocationResponse> delete(@PathVariable UUID id) {
        locationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
