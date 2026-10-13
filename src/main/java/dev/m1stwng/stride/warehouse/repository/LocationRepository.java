package dev.m1stwng.stride.warehouse.repository;

import dev.m1stwng.stride.warehouse.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    boolean existsByCode(String code);

    List<Location> findAllByWarehouseId(UUID id);
}
