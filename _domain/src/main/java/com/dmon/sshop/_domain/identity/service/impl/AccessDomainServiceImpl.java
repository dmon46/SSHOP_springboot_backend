package com.dmon.sshop._domain.identity.service.impl;

import com.dmon.sshop._domain.identity.repository.IAccountDomainRepository;
import org.springframework.stereotype.Service;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.service.IAccessDomainService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccessDomainServiceImpl implements IAccessDomainService {
    IAccountDomainRepository accountDomainRepository;

    SellerDomainServiceImpl sellerService;

    // SIGN UP//
    @Override
    public AccountRes.Signup signup(AccountReq.Create accessDto, Account.RoleEnum role) {
        // apply factory pattern
        // todo: build sup, sub classes to follow factory pattern
        if (role.equals(Account.RoleEnum.ADMIN))
            throw new AppException(ErrorCode.SYSTEM__DEVELOPING_FEATURE);
        if (role.equals(Account.RoleEnum.SELLER))
            return this.sellerService.create(accessDto);
        if (role.equals(Account.RoleEnum.USER))
            throw new AppException(ErrorCode.SYSTEM__DEVELOPING_FEATURE);
        throw new AppException(ErrorCode.SYSTEM__ENUM_KEY_INVALID); // todo: build a supported exception
    }
}
