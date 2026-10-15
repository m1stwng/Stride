package dev.m1stwng.stride.procurement.supplier.entity;

import dev.m1stwng.stride.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "suppliers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Supplier extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String phone;
}
