package dmon.SSHOP_springboot_backend.mapper.product;

import dmon.SSHOP_springboot_backend.dto.request.product.ProdItemCreateReqV2;
import dmon.SSHOP_springboot_backend.entity.inventory.Inventory;
import dmon.SSHOP_springboot_backend.entity.product.Product;
import dmon.SSHOP_springboot_backend.entity.product.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@Mapper(componentModel = "spring")
public interface IProdItemMapperV2 {
    //THỬ NGHIỆM MAP PHẲNG NESTED OBJECT

    // Map from ProductDto to Product
//    @Mapping(target = "category.id", source = "categoryId")
//    @Mapping(target = "skus", source = "skus")
    Product toEntity(ProdItemCreateReqV2 productDto);

    // Map from SkuDto to Sku
//    @Mapping(target = "inventory.stock", source = "stock")
    Sku toEntity(ProdItemCreateReqV2.SkuReq skuDto);

    // Optional: If needed, can add the custom method to map stock to Inventory.stock
    default Inventory mapStockToInventory(Integer stock) {
        if (stock == null) {
            return null;
        }
        Inventory inventory = new Inventory();
        inventory.setStocks(stock);
        return inventory;
    }
}
