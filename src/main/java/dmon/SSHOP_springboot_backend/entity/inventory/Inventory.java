package dmon.SSHOP_springboot_backend.entity.inventory;

import dmon.SSHOP_springboot_backend.entity.base.BaseEntity;
import dmon.SSHOP_springboot_backend.entity.product.Sku;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "inventories")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE inventories SET deleted = true WHERE sku_id = ?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Inventory extends BaseEntity {
    @Id
    String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skuId", updatable = false, nullable = false)
    Sku sku;

    Integer total;

    Integer stocks;

    Integer sales;
}
