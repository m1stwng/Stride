package dev.m1stwng.stride.inventory.repository;

import dev.m1stwng.stride.inventory.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    Page<Inventory> findAllByProductId(UUID productId, Pageable pageable);

    Page<Inventory> findAllByLocationId(UUID locationId, Pageable pageable);
}
