package com.dmon.sshop._domain.inventory.model.entity;

import com.dmon.sshop._domain.common.base.BaseEntity;
import com.dmon.sshop._domain.product.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;

import java.util.ArrayList;

@Entity
@Table(name = "skus")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE skus SET deleted = true WHERE sku_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sku extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "skuId", updatable = false, nullable = false)
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    @JsonIgnore
    Product product;

    @OneToOne(mappedBy = "sku", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    Inventory inventory;

    String status; //LIVE, DEACTIVATED

    @Column(nullable = false)
    String skuCode;

    @Column()
    String tierName;

    @Column()
    Integer[] tierIndex;

    Float productCost;

    Float retailPrice;

    Integer carts;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ArrayList<Specification> specifications;

    // THE NESTED OBJECTS//
    public enum StatusEnum { LIVE, DEACTIVATED, }

    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Specification {
        String name;

        String value;
    }

}
