package com.dmon.sshop._domain.identity.servicev2;

import com.dmon.sshop._domain.identity.model.entity.Account;

public interface IAccessDomainService {
    Object login(Object AccountLoginReq, Account.RoleType roleType);
    Object logout(Account.RoleType roleType);
    Object signup(Object AccountSignupReq, Account.RoleType roleType);
    Object fotgotPassword(Account.RoleType roleType);
}
