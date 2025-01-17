package com.dmon.sshop._application.service.identity;

import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Account;

import java.util.List;

public interface IAccountAppService {
    AccountRes createOne(AccountReq.Create accountDto);
    Account preparePreCreate(AccountReq.Create accountDto, Account.RoleType roleType);
    AccountRes updateOne(String accountId, AccountReq.Update body);
    Void deleteOne(String accountId);
    List<Account> listAll();
    AccountRes findOne(String accountId);
    AccountRes findMyOne();
}
