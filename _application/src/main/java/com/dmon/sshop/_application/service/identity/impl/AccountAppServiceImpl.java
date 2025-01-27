package com.dmon.sshop._application.service.identity.impl;

import com.dmon.sshop._application.service.identity.IAccountAppService;
import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.service.IAccountDomainService;
import com.dmon.sshop._infrastructure.security.impl.SecurityInfraHelperImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountAppServiceImpl implements IAccountAppService {
    IAccountDomainService accountDomainService;

    SecurityInfraHelperImpl securityHelper;
    @Override
    public AccountRes createOne(AccountReq.Create accountDto) {
        return null;
//        accountDto.setPassword(this.securityHelper.hashPassword(accountDto.getPassword()));

//        return this.accountDomainService.createOne(accountDto);
    }

    @Override
    public Account preparePreCreate(AccountReq.Create accountDto, Account.RoleType roleType) {
        return null;
//        return this.accountDomainService.preparePreCreate(accountDto, roleType);
    }

    @Override
    public AccountRes updateOne(String accountId, AccountReq.Update body) {
        return null;
//        return this.accountDomainService.updateOne(accountId, body);
    }

    @Override
    public Void deleteOne(String accountId) {
        return null;
//        this.accountDomainService.deleteOne(accountId);
//        return null;
    }

    @Override
    public List<Account> listAll() {
        return null;
//        return this.accountDomainService.listAll();
    }

    @Override
    public AccountRes findOne(String accountId) {
        return null;
//        return this.accountDomainService.findOne(accountId);
    }

    @Override
    public AccountRes findMyOne() {
        return null;
//        return this.accountDomainService.findMyOne();
    }
}
