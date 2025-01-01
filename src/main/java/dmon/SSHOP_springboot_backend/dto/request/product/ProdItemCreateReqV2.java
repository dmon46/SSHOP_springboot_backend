package dmon.SSHOP_springboot_backend.dto.request.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import dmon.SSHOP_springboot_backend.entity.product.Product;
import dmon.SSHOP_springboot_backend.entity.product.Sku;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdItemCreateReqV2 {

    String categoryId;

    @NotEmpty(message = "")
    List<ProdItemCreateReqV2.SkuReq> skus;

    @NotEmpty(message = "")
    String name;

    String description;

    @NotEmpty(message = "")
    String thumb;

    List<String> photos;

    String video;

    String sizeChart;

    @NotEmpty(message = "")
    Float basePrice;

    @NotEmpty(message = "")
    Float weight;

    @NotEmpty(message = "")
    String location;

    ArrayList<Product.Attribute> attributes;

    ArrayList<Product.TierVariation> tierVariations;

    //THE NESTED CLASSES//
    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SkuReq {
        Integer stock;

        String no;

        Integer[] tierIndex;

        Float productCost;

        Float basePrice;

        private ArrayList<Sku.Specification> specifications;
    }
}
