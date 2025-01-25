package com.dmon.sshop._domain.identity.servicev2.impl;

import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.servicev2.IAccountDomainService;
import com.dmon.sshop._domain.identity.servicev2.impl.factory.AccountServiceFactory;
import com.dmon.sshop._domain.identity.servicev2.impl.factory.IAccountService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountDomainServiceImpl implements IAccountDomainService {

    AccountServiceFactory accountServiceFactory;

    @Override
    public Object createAdmin(Object AccountCreateReq) {
        return null;
    }

    @Override
    public Object getAccount(Account.RoleType roleType) {
        IAccountService accountService = this.accountServiceFactory.get(roleType);
        return accountService.getAccount();
    }

    @Override
    public Object getProfile(Account.RoleType roleType) {
        IAccountService accountService = this.accountServiceFactory.get(roleType);
        return accountService.getProfile();
    }
}
