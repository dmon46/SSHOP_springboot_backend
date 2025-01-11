package dmon.SSHOP_springboot_backend._repository.account;

import dmon.SSHOP_springboot_backend.entity.account.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISellerRepository extends JpaRepository<Seller, String> {

}
