package com.dmon.sshop._domain.identity.service.impl;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.mapper.IAccountMapper;
import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.repository.IAccountDomainRepository;
import com.dmon.sshop._domain.identity.service.IAccountDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountDomainServiceImpl implements IAccountDomainService {

    IAccountDomainRepository accountRepo;
    IAccountMapper accountMapper;

    //CREATE//
    @Override
    public AccountRes createOne(AccountReq.Create accountDto) {
        //check that email, phone should be not existed
        if (this.accountRepo.existsByUsername(accountDto.getEmail()))
            throw new AppException(ErrorCode.ACCOUNT__USERNAME_UNIQUE);
        if (this.accountRepo.existsByEmail(accountDto.getEmail()))
            throw new AppException(ErrorCode.ACCOUNT__EMAIL_UNIQUE);
        if (this.accountRepo.existsByPhone(accountDto.getPhone()))
            throw new AppException(ErrorCode.ACCOUNT__PHONE_UNIQUE);
        //prepare entity to created: modify password, role fields
        Account accountCreated = this.accountMapper.toEntity(accountDto);

        accountCreated.setRoles(new HashSet<>(Collections.singletonList(Account.RoleType.BUYER.name())));

        Account accountResult = this.accountRepo.save(accountCreated);
        return this.accountMapper.toRes(accountResult);
    }

    @Override
    public Account preparePreCreate(AccountReq.Create accountDto, Account.RoleType roleType) {
        Account accountFound = this.accountRepo.findByUsername(accountDto.getUsername()).orElse(null);
        if (Objects.nonNull(accountFound)) {
            //if account is present, throw exception
            if (accountFound.getRoles().contains(Account.RoleType.BUYER.name()))
                throw new AppException(ErrorCode.SECURITY__USER_NOT_REGISTER_SELLER);
            if (accountFound.getRoles().contains(Account.RoleType.SELLER.name()))
                throw new AppException(ErrorCode.SECURITY__SELLER_NOT_REGISTER_SELLER);
            if (accountFound.getRoles().contains((Account.RoleType.ADMIN.name())))
                throw new AppException(ErrorCode.SECURITY__ADMIN_NOT_REGISTER_SELLER);
        }
        //if account isn't present, create new
        Account accountCreated = Account.builder()
                .username(accountDto.getUsername())
//                .password(this.securityHelper.hashPassword(accountDto.getPassword())) //todo
                .roles(new HashSet<>(Collections.singletonList(roleType.name())))
                .build();

        return accountCreated;
    }

    //UPDATE//
    @Override
    public AccountRes updateOne(String accountId, AccountReq.Update body) {
        //check
        this.findOne(accountId);
        Account account = this.accountMapper.toEntity(body);
        account.setId(accountId);
        account = this.accountRepo.save(account);
        return this.accountMapper.toRes(account);
    }

    //DELETE//
    @Override
    public void deleteOne(String accountId) {
        accountRepo.deleteById(accountId);
    }

    //LIST ALL//
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> listAll() {
        List<Account> accounts;
        accounts = this.accountRepo.findAll();
        return accounts;
    }

    //FIND ONE//
    @Override
    @PostAuthorize("returnObject.id == authentication.name")
    public AccountRes findOne(String accountId) {
        Account account = this.accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT__NOT_FOUND));
        return this.accountMapper.toRes(account);
    }

    @Override
    public AccountRes findMyOne() {
        var context = SecurityContextHolder.getContext();
        String accountId = context.getAuthentication().getName();

        return this.findOne(accountId);
    }

    //HELPER//
    @Override
    public Account findOrError(String accountId) {
        Account accountPresent = this.accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT__NOT_FOUND));

        return accountPresent;
    }
}
