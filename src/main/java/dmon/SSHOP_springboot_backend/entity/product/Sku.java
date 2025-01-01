package dmon.SSHOP_springboot_backend.entity.product;

import java.util.ArrayList;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dmon.SSHOP_springboot_backend.entity.base.BaseEntity;
import dmon.SSHOP_springboot_backend.entity.inventory.Inventory;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

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
    Product product;

    @OneToOne(mappedBy = "sku", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore @ToString.Exclude
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
    private ArrayList<Sku.Specification> specifications;

    // THE NESTED CLASS//
    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Specification {
        String name;

        String value;
    }

}
