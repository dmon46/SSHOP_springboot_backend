package com.dmon.sshop._application.external.security.impl;

import com.dmon.sshop._application.external.security.IAccessSecurityService;
import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.repository.IAccountDomainRepository;
import com.dmon.sshop._infrastructure.security.ISecurityInfraHelper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccessSecurityServiceImpl implements IAccessSecurityService {

    IAccountDomainRepository accountDomainRepository;
    ISecurityInfraHelper securityInfraHelper;

    @Override
    public AccountRes.Access login(AccountReq.Login loginDto, Account.RoleType roleType) {
        Account account = this.accountDomainRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.SECURITY__LOGIN_FAILED));

        if (!account.getRoles().contains(roleType.toString()))
            throw new AppException(ErrorCode.SECURITY__UNAUTHORIZED);

        if (!this.securityInfraHelper.matchPassword(loginDto.getPassword(), account.getPassword()))
            throw new AppException(ErrorCode.SECURITY__LOGIN_FAILED);

        String token = this.securityInfraHelper.genToken(account);

        return AccountRes.Access
                .builder()
                .authenticated(true)
                .token(token)
                .build();
    }

    @Override
    public Void logout() {
        this.accountDomainRepository.findById(this.securityInfraHelper.getAccountId())
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT__NOT_FOUND));
        return null;
    }
}
