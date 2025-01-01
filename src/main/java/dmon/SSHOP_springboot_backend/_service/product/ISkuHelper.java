package dmon.SSHOP_springboot_backend._service.product;

import dmon.SSHOP_springboot_backend.dto.request.product.ProductCreateReq;
import dmon.SSHOP_springboot_backend.entity.account.Seller;
import dmon.SSHOP_springboot_backend.entity.product.Product;
import dmon.SSHOP_springboot_backend.entity.product.Sku;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISkuHelper {
    List<Sku> preparePreCreate(Product productCreated);
}
