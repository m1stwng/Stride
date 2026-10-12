package dev.m1stwng.stride.product.repository;

import dev.m1stwng.stride.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    boolean existsBySku(String sku);

    boolean existsBySkuAndIdNot(String sku, UUID id);
}
