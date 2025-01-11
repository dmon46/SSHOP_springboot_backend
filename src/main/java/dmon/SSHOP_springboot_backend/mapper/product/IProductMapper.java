package dmon.SSHOP_springboot_backend.mapper.product;

import dmon.SSHOP_springboot_backend.dto.request.product.ProductCreateReq;
import dmon.SSHOP_springboot_backend.dto.response.product.ProductRes;
import dmon.SSHOP_springboot_backend.entity.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    Product toEntity(ProductCreateReq productDto);
    ProductRes toRes(Product entity);
}
