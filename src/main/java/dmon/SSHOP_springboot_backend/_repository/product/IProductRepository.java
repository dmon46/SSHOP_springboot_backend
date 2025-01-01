package dmon.SSHOP_springboot_backend._repository.product;

import dmon.SSHOP_springboot_backend._repository.product.proj.IProductProjection;
import dmon.SSHOP_springboot_backend.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    //LIST//
    Page<IProductProjection> findAllProjectedBy(Pageable pageable);
}
