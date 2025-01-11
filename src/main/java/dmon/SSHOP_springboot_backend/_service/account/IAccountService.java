package dmon.SSHOP_springboot_backend._service.account;

import dmon.SSHOP_springboot_backend.dto.request.account.AccountCreateReq;
import dmon.SSHOP_springboot_backend.entity.account.Account;
import dmon.SSHOP_springboot_backend.enums.RoleEnum;
import org.springframework.stereotype.Service;

@Service
public interface IAccountService {
    Account preparePreCreate(AccountCreateReq accountDto, RoleEnum roleEnum);
}
