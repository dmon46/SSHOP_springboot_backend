package dmon.SSHOP_springboot_backend._repository.product;

import dmon.SSHOP_springboot_backend.entity.product.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISkuRepository extends JpaRepository<Sku, String> {
    Optional<Sku> findBySkuCode(String skuCode);
}
