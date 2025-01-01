package dmon.SSHOP_springboot_backend._service.product.impl;

import dmon.SSHOP_springboot_backend._service.product.IProductMetricHelper;
import dmon.SSHOP_springboot_backend.entity.product.Product;
import dmon.SSHOP_springboot_backend.entity.product.ProductMetric;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductMetricServiceImpl implements IProductMetricHelper {

    @Override
    public ProductMetric preparePreCreate(Product productCreated) {
        ProductMetric productMetric = ProductMetric.builder()
                .product(productCreated)
                .sales(0)
                .views(0)
                .carts(0)
                .stocks(productCreated.getSkus().stream().parallel()
                        .map(sku -> sku.getInventory().getStocks())
                        .reduce(0, Integer::sum)
                )
                .build();

        return productMetric;
    }
}
