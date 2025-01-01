package dmon.SSHOP_springboot_backend.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import dmon.SSHOP_springboot_backend.entity.account.Seller;
import dmon.SSHOP_springboot_backend.entity.base.BaseEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE product_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "productId", updatable = false, nullable = false)
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId", updatable = false, nullable = false)
    Seller seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    Category category;

    @OneToOne(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY) //todo: edit the api creating a product
    ProductMetric metric;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) //orphanRemoval: xóa các child mồ coi
    @JsonIgnore @ToString.Exclude
    List<Sku> skus;

    String status; //DRAFT, REVIEWING, LIVE, DEACTIVATED, SUSPENDED, DELETED

    @Column(nullable = false)
    String name;

    @Column(columnDefinition = "text")
    String description;

    String slug;

    @Column(nullable = false)
    String thumb;

    List<String> photos;

    String video;

    String sizeChart;

    Float retailPrice;

    Float weight; //unit in grams (g)

    String location;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    ArrayList<Attribute> attributes;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    ArrayList<TierVariation> tierVariations;

    //THE NESTED CLASSES//
    @Data
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Attribute {
        String name;

        String value;

        String link;
    }

    @Data
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TierVariation {
        String name;

        ArrayList<String> options;

        ArrayList<String> photos;
    }
}
