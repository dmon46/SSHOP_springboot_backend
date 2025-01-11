package dmon.SSHOP_springboot_backend.dto.response.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import dmon.SSHOP_springboot_backend.entity.product.Product;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRes {
    String id;

    String status; //REVIEWING, LIVE, DEACTIVATED, SUSPENDED, CLOSED

    boolean inStock;

    String name;

    String description;

    String slug;

    String thumb;

    List<String> photos;

    String video;

    String sizeChart;

    Float basePrice;

    Float weight; //unit in grams (g)

    String location;

    ArrayList<Product.Attribute> attributes;

    ArrayList<Product.TierVariation> tierVariations;
}
