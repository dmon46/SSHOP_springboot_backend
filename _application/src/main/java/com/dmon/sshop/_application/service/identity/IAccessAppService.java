package com.dmon.sshop._application.service.identity;

import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Account;

public interface IAccessAppService {
    AccountRes.Access login(AccountReq.Login request, Account.RoleType role);
    Void logout();
    Object signup(AccountReq.Create accessDto, Account.RoleType role);
}
