package dmon.SSHOP_springboot_backend._service.product;

import dmon.SSHOP_springboot_backend._repository.product.proj.IProductProjection;
import dmon.SSHOP_springboot_backend.base.PageRes;
import dmon.SSHOP_springboot_backend.dto.request.product.ProductCreateReq;
import dmon.SSHOP_springboot_backend.dto.response.product.ProductRes;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IProductService {
    //CREATE//
    ProductRes create(ProductCreateReq productDto);

    //LIST//
    PageRes<IProductProjection> findAll(Pageable pageable);

}
