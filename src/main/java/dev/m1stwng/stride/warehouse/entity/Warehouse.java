package dev.m1stwng.stride.warehouse.entity;

import dev.m1stwng.stride.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "warehouses")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Warehouse extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @Column
    private String alias;
}
