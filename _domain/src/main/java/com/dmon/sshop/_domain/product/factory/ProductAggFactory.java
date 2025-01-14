package com.dmon.sshop._domain.product.factory;

import com.dmon.sshop._domain.common.util.AppUtil;
import com.dmon.sshop._domain.inventory.factory.SkuAggFactory;
import com.dmon.sshop._domain.product.model.entity.Product;
import com.dmon.sshop._domain.inventory.model.entity.Sku;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductAggFactory {
    SkuAggFactory skuAggFactory;

    ProductMetricAggFactory metricAggFactory;

    //CREATE//
    public Product create(Product product) {
        product.setStatus(Product.StatusEnum.REVIEWING.name());

        product.setSlug(this.genSlug(product.getName()));

        product.setSkus(this.skuAggFactory.create(product)); //cascade

        product.setRetailPrice(this.genRetailPrice(product.getSkus()));

        product.setMetric(this.metricAggFactory.create(product));

        return product;
    }

    //FIELDS//
    public float genRetailPrice(List<Sku> skus) {
        float retailPrice = (float) skus.stream().parallel()
                .mapToDouble(Sku::getRetailPrice)
                .average()
                .orElse(0.0);

        return retailPrice;
    }

    public String genSlug(String name) {
        String slug = AppUtil.toSlug(name);

        return slug;
    }
}
