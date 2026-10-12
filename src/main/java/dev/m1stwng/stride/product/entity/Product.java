package dev.m1stwng.stride.product.entity;

import dev.m1stwng.stride.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;
}
