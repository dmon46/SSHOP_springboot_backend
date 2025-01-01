package dmon.SSHOP_springboot_backend._service.product;

import dmon.SSHOP_springboot_backend.entity.product.Category;
import org.springframework.stereotype.Service;

@Service
public interface ICategoryHelper {
    Category findEntity(String cateId);
}
