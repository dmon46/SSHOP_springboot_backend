package com.dmon.sshop._domain.inventory.factory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dmon.sshop._domain.common.util.AppUtil;
import com.dmon.sshop._domain.product.model.entity.Product;
import com.dmon.sshop._domain.inventory.model.entity.Sku;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SkuAggFactory {
    InventoryAggFactory invAggFactory;

    // CREATE//
    public List<Sku> create(Product product) {
        List<Sku> skus = product.getSkus();
        skus.stream().parallel()
                .forEach(sku -> {
                    sku.setProduct(product);

                    sku.setStatus(Sku.StatusEnum.LIVE.name());

                    if (AppUtil.isEmpty(sku.getSkuCode()))
                        sku.setSkuCode(this.genSkuCode());

                    sku.setCarts(0);

                    sku.setInventory(this.invAggFactory.create(sku)); // cascade
                });

        return skus;
    }

    // FIELDS//
    public String genSkuCode() {
        String skuCode = AppUtil.genUUID();

        return skuCode;
    }
}
