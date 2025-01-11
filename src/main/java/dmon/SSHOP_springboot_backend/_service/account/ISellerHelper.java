package dmon.SSHOP_springboot_backend._service.account;

import dmon.SSHOP_springboot_backend.entity.account.Seller;
import org.springframework.stereotype.Service;

@Service
public interface ISellerHelper {
    //FIND//
    Seller findCurrentLogin();
}
