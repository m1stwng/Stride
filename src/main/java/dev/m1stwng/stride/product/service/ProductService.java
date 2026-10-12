package dev.m1stwng.stride.product.service;

import dev.m1stwng.stride.product.dto.request.CreateProductRequest;
import dev.m1stwng.stride.product.dto.request.UpdateProductRequest;
import dev.m1stwng.stride.product.dto.response.ProductResponse;
import dev.m1stwng.stride.product.entity.Product;
import dev.m1stwng.stride.product.exception.DuplicateProductException;
import dev.m1stwng.stride.product.exception.ProductNotFoundException;
import dev.m1stwng.stride.product.mapper.ProductMapper;
import dev.m1stwng.stride.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toResponse);
    }

    public ProductResponse findById(UUID id) {
        return productRepository.findById(id)
                .map(productMapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public ProductResponse create(CreateProductRequest request) {
        final String sku = request.sku().trim().toUpperCase(Locale.ROOT);

        if (productRepository.existsBySku(sku)) {
            throw new DuplicateProductException(sku);
        }

        final Product product = Product.builder()
                .sku(sku)
                .name(request.name())
                .build();

        return productMapper.toResponse(productRepository.save(product));
    }

    public ProductResponse update(UUID id, UpdateProductRequest request) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        final String sku = request.sku().trim().toUpperCase(Locale.ROOT);

        if (productRepository.existsBySkuAndIdNot(sku, id)) {
            throw new DuplicateProductException(sku);
        }

        product.setSku(sku);
        product.setName(request.name());

        return productMapper.toResponse(productRepository.save(product));
    }

    public void delete(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }

        productRepository.deleteById(id);
    }
}
