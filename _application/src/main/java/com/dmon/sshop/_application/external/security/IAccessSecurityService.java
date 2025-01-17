package com.dmon.sshop._application.external.security;

import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Account;

public interface IAccessSecurityService {
    AccountRes.Access login(AccountReq.Login request, Account.RoleType role);
    Void logout();
}
