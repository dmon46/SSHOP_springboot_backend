package com.dmon.sshop._domain.identity.servicev2.impl;

import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.servicev2.IAccessDomainService;
import com.dmon.sshop._domain.identity.servicev2.impl.factory.AccessServiceFactory;
import com.dmon.sshop._domain.identity.servicev2.impl.factory.IAccessService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccessDomainServiceImpl implements IAccessDomainService {

    AccessServiceFactory accessServiceFactory;

    @Override
    public Object login(Object accountLoginReq, Account.RoleType roleType) {
        IAccessService accessService = this.accessServiceFactory.get(roleType);
        return accessService.login(accountLoginReq);
    }

    @Override
    public Object logout(Account.RoleType roleType) {
        IAccessService accessService = this.accessServiceFactory.get(roleType);
        return accessService.logout();
    }

    @Override
    public Object signup(Object accountSignupReq, Account.RoleType roleType) {
        IAccessService accessService = this.accessServiceFactory.get(roleType);
        return accessService.signup(accountSignupReq);
    }

    @Override
    public Object fotgotPassword(Account.RoleType roleType) {
        IAccessService accessService = this.accessServiceFactory.get(roleType);
        return accessService.forgotPassword();
    }
}
