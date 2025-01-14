package com.dmon.sshop._application.service.identity.impl;

import com.dmon.sshop._application.service.identity.IAccessAppService;
import com.dmon.sshop._application.external.security.IAccessSecurityService;
import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.service.IAccessDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccessAppServiceImpl implements IAccessAppService {
    IAccessDomainService accessDomainService;

    IAccessSecurityService accessSecurityService;
    @Override
    public AccountRes.Access login(AccountReq.Login loginDto, Account.RoleEnum roleEnum) {
        return this.accessSecurityService.login(loginDto, roleEnum);
    }

    @Override
    public Void logout() {
        return this.accessSecurityService.logout();
    }

    @Override
    public AccountRes.Signup signup(AccountReq.Create accessDto, Account.RoleEnum role) {
        return this.accessDomainService.signup(accessDto, role);
    }
}
