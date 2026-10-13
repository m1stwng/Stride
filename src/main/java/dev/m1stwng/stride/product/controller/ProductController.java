package dev.m1stwng.stride.product.controller;

import dev.m1stwng.stride.inventory.dto.response.InventoryResponse;
import dev.m1stwng.stride.inventory.service.InventoryService;
import dev.m1stwng.stride.product.dto.request.CreateProductRequest;
import dev.m1stwng.stride.product.dto.request.UpdateProductRequest;
import dev.m1stwng.stride.product.dto.response.ProductResponse;
import dev.m1stwng.stride.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final InventoryService inventoryService;
    private final ProductService productService;

    @GetMapping
    ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/{id}/inventories")
    ResponseEntity<Page<InventoryResponse>> findAllInventoriesByProductId(@PathVariable UUID id, Pageable pageable) {
        return ResponseEntity.ok(inventoryService.findAllByProductId(id, pageable));
    }

    @PostMapping
    ResponseEntity<ProductResponse> create(@RequestBody @Valid CreateProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductResponse> update(@PathVariable UUID id, @RequestBody @Valid UpdateProductRequest request) {
        return ResponseEntity.ok(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ProductResponse> delete(@PathVariable UUID id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
