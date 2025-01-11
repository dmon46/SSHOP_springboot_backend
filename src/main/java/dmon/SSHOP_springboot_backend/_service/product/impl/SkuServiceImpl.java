package dmon.SSHOP_springboot_backend._service.product.impl;

import dmon.SSHOP_springboot_backend._repository.product.ISkuRepository;
import dmon.SSHOP_springboot_backend._service.product.ISkuHelper;
import dmon.SSHOP_springboot_backend.base.AppException;
import dmon.SSHOP_springboot_backend.base.ExceptionCode;
import dmon.SSHOP_springboot_backend.entity.inventory.Inventory;
import dmon.SSHOP_springboot_backend.entity.product.Product;
import dmon.SSHOP_springboot_backend.entity.product.Sku;
import dmon.SSHOP_springboot_backend.enums.StatusEnum;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SkuServiceImpl implements ISkuHelper {
    ISkuRepository skuRepo;

    @Override
    public List<Sku> preparePreCreate(Product productCreated) {
        List<Sku> skusCreated = productCreated.getSkus();
        skusCreated.stream().parallel()
                .forEach(sku -> {
                    sku.setProduct(productCreated);

                    sku.setStatus(StatusEnum.Sku.LIVE.name());

                    String skuCode = productCreated.getSeller().getShopCode() + sku.getSkuCode();
                    skuRepo.findBySkuCode(skuCode)
                            .ifPresent(ignored -> { throw new AppException(ExceptionCode.SKU__CODE_UNIQUE); });
                    sku.setSkuCode(skuCode);

                    sku.setCarts(0);

                    Inventory inventory = Inventory.builder()
                            .sku(sku)
                            .total(sku.getInventory().getStocks())
                            .stocks(sku.getInventory().getStocks())
                            .sales(0)
                            .build();
                    sku.setInventory(inventory); //cascade
                });

        return skusCreated;
    }
}
