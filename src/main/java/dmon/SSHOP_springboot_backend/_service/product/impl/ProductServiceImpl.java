package dmon.SSHOP_springboot_backend._service.product.impl;

import dmon.SSHOP_springboot_backend._repository.product.IProductRepository;
import dmon.SSHOP_springboot_backend._repository.product.proj.IProductProjection;
import dmon.SSHOP_springboot_backend._service.account.ISellerHelper;
import dmon.SSHOP_springboot_backend._service.product.ICategoryHelper;
import dmon.SSHOP_springboot_backend._service.product.IProductMetricHelper;
import dmon.SSHOP_springboot_backend._service.product.IProductService;
import dmon.SSHOP_springboot_backend._service.product.ISkuHelper;
import dmon.SSHOP_springboot_backend.base.PageRes;
import dmon.SSHOP_springboot_backend.dto.request.product.ProductCreateReq;
import dmon.SSHOP_springboot_backend.dto.response.product.ProductRes;
import dmon.SSHOP_springboot_backend.entity.product.Product;
import dmon.SSHOP_springboot_backend.entity.product.Sku;
import dmon.SSHOP_springboot_backend.enums.StatusEnum;
import dmon.SSHOP_springboot_backend.mapper.product.IProductMapper;
import dmon.SSHOP_springboot_backend.utils.AppUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductServiceImpl implements IProductService {
    IProductRepository productRepo;

    IProductMapper productMapper;

    ISellerHelper sellerHelper;

    ICategoryHelper categoryHelper;

    ISkuHelper skuHelper;

    IProductMetricHelper metricHelper;

    //CREATE//
    @Override
    public ProductRes create(ProductCreateReq productDto) {
        Product productCreated = this.productMapper.toEntity(productDto);

        productCreated.setSeller(this.sellerHelper.findCurrentLogin());

        productCreated.setCategory(this.categoryHelper.findEntity(
                productCreated.getCategory().getId()));

        productCreated.setStatus(StatusEnum.Product.REVIEWING.name());

        productCreated.setSlug(AppUtil.toSlug(productDto.getName()));

        productCreated.setSkus(this.skuHelper.preparePreCreate(productCreated)); //cascade

        productCreated.setRetailPrice(
                (float) productCreated.getSkus().stream().parallel()
                        .mapToDouble(Sku::getRetailPrice)
                        .average()
                        .orElse(0.0)
        );

        productCreated.setMetric(this.metricHelper.preparePreCreate(productCreated));

        Product productResult = this.productRepo.save(productCreated);
        return this.productMapper.toRes(productResult);
    }

    //LIST//
    public PageRes<IProductProjection> findAll(Pageable pageable) {
        Page<IProductProjection> productPage = this.productRepo.findAllProjectedBy(pageable);
        return  AppUtil.toPageRes(productPage);
    }
}
