package dev.m1stwng.stride.inventory.entity;

import dev.m1stwng.stride.common.entity.BaseEntity;
import dev.m1stwng.stride.product.entity.Product;
import dev.m1stwng.stride.warehouse.entity.Location;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventories")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private int quantity;
}
