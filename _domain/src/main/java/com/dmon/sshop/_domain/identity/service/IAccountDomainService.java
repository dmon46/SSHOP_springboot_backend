package com.dmon.sshop._domain.identity.service;

import java.util.List;

import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Account;

public interface IAccountDomainService {
    // CREATE//
    AccountRes createOne(AccountReq.Create accountDto);
    Account preparePreCreate(AccountReq.Create accountDto, Account.RoleType roleType);

    //READ//
    List<Account> listAll();
    AccountRes findOne(String accountId);
    AccountRes findMyOne();

    // UPDATE//
    AccountRes updateOne(String accountId, AccountReq.Update body);

    // DELETE//
    void deleteOne(String accountId);

    //HELPER//
    Account findOrError(String accountId);
}
