package dev.m1stwng.stride.procurement.supplier.controller;

import dev.m1stwng.stride.procurement.supplier.dto.request.CreateSupplierRequest;
import dev.m1stwng.stride.procurement.supplier.dto.request.UpdateSupplierRequest;
import dev.m1stwng.stride.procurement.supplier.dto.response.SupplierResponse;
import dev.m1stwng.stride.procurement.supplier.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    ResponseEntity<Page<SupplierResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(supplierService.findAll(pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<SupplierResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(supplierService.findById(id));
    }

    @PostMapping
    ResponseEntity<SupplierResponse> create(@RequestBody @Valid CreateSupplierRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.create(request));
    }

    @PutMapping("/{id}")
    ResponseEntity<SupplierResponse> update(@PathVariable UUID id, @RequestBody @Valid UpdateSupplierRequest request) {
        return ResponseEntity.ok(supplierService.update(id, request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<SupplierResponse> delete(@PathVariable UUID id) {
        supplierService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
