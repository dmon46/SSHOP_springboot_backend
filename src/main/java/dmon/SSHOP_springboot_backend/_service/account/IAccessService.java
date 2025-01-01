package dmon.SSHOP_springboot_backend._service.account;


import dmon.SSHOP_springboot_backend.dto.request.account.AccountCreateReq;
import dmon.SSHOP_springboot_backend.dto.request.account.LoginReq;
import dmon.SSHOP_springboot_backend.dto.request.account.SignupReq;
import dmon.SSHOP_springboot_backend.dto.response.account.AccessRes;
import dmon.SSHOP_springboot_backend.dto.response.account.SellerRes;
import dmon.SSHOP_springboot_backend.dto.response.account.SignupRes;
import dmon.SSHOP_springboot_backend.enums.RoleEnum;
import org.springframework.stereotype.Service;

@Service
public interface IAccessService {
    AccessRes login(LoginReq request, RoleEnum role);
    void logout();
    SignupRes signup(AccountCreateReq accessDto, RoleEnum role);
}
