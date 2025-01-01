package dmon.SSHOP_springboot_backend._service.account.impl;

import dmon.SSHOP_springboot_backend._repository.account.IAccountRepository;
import dmon.SSHOP_springboot_backend._service.account.IAccessService;
import dmon.SSHOP_springboot_backend.base.AppException;
import dmon.SSHOP_springboot_backend.base.ExceptionCode;
import dmon.SSHOP_springboot_backend.dto.request.account.AccountCreateReq;
import dmon.SSHOP_springboot_backend.dto.request.account.LoginReq;
import dmon.SSHOP_springboot_backend.dto.response.account.AccessRes;
import dmon.SSHOP_springboot_backend.dto.response.account.SignupRes;
import dmon.SSHOP_springboot_backend.entity.account.Account;
import dmon.SSHOP_springboot_backend.enums.RoleEnum;
import dmon.SSHOP_springboot_backend.security.SecurityHelper;
import dmon.SSHOP_springboot_backend.security.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccessServiceImpl implements IAccessService {
    IAccountRepository accountRepo;

    SellerServiceImpl sellerService;

    SecurityHelper securityHelper;

    //LOG IN, OUT//
    @Override
    public AccessRes login(LoginReq request, RoleEnum roleEnum) {
        Account account = this.accountRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ExceptionCode.ACCESS__LOGIN_FAILED));

        if (!account.getRoles().contains(roleEnum.toString()))
            throw new AppException(ExceptionCode.ACCESS__UNAUTHORIZED);

        if (!this.securityHelper.matchPassword(request.getPassword(), account.getPassword()))
            throw new AppException(ExceptionCode.ACCESS__LOGIN_FAILED);

        var token = this.securityHelper.genToken(account);

        return AccessRes
                .builder()
                .authenticated(true)
                .token(token)
                .build();
    }

    @Override
    public void logout() {
        String accountId = SecurityUtil.getCurrentAccount()
                .orElseThrow(() -> new AppException(ExceptionCode.SYSTEM__UNHANDLED_EXCEPTION));
        System.out.println(accountId);
        this.accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ExceptionCode.ACCOUNT__NOT_FOUND));
    }

    //SIGN UP//
    @Override
    public SignupRes signup(AccountCreateReq accessDto, RoleEnum role) {
        //apply factory pattern
        //todo: build sup, sub classes to follow factory pattern
        if (role.equals(RoleEnum.ADMIN))
            throw new AppException(ExceptionCode.SYSTEM__DEVELOPING_EXCEPTION);
        if (role.equals(RoleEnum.SELLER))
            return this.sellerService.create(accessDto);
        if (role.equals(RoleEnum.USER))
            throw new AppException(ExceptionCode.SYSTEM__DEVELOPING_EXCEPTION);
        throw new AppException(ExceptionCode.SYSTEM__ENUM_KEY_INVALID); //todo: build a supported exception
    }
}
