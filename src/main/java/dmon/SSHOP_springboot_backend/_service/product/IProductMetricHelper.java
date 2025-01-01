package dmon.SSHOP_springboot_backend._service.product;

import dmon.SSHOP_springboot_backend.entity.product.Product;
import dmon.SSHOP_springboot_backend.entity.product.ProductMetric;
import org.springframework.stereotype.Service;

@Service
public interface IProductMetricHelper {
    ProductMetric preparePreCreate(Product productCreated);
}
