package com.dmon.sshop._domain.identity.servicev2;

import com.dmon.sshop._domain.identity.model.entity.Account;

public interface IAccountDomainService {
    Object createAdmin(Object AccountCreateReq);
    Object getAccount(Account.RoleType roleType);
    Object getProfile(Account.RoleType roleType);

}
