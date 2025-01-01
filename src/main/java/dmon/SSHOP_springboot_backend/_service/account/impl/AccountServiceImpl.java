package dmon.SSHOP_springboot_backend._service.account.impl;

import dmon.SSHOP_springboot_backend._repository.account.IAccountRepository;
import dmon.SSHOP_springboot_backend._service.account.IAccessService;
import dmon.SSHOP_springboot_backend._service.account.IAccountService;
import dmon.SSHOP_springboot_backend.dto.request.account.AccountCreateReq;
import dmon.SSHOP_springboot_backend.dto.request.account.AccountUpdateReq;
import dmon.SSHOP_springboot_backend.dto.response.account.AccountRes;
import dmon.SSHOP_springboot_backend.entity.account.Account;
import dmon.SSHOP_springboot_backend.base.AppException;
import dmon.SSHOP_springboot_backend.base.ExceptionCode;

import dmon.SSHOP_springboot_backend.mapper.account.IAccountMapper;
import dmon.SSHOP_springboot_backend.enums.RoleEnum;
import dmon.SSHOP_springboot_backend.security.SecurityHelper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements IAccountService {
    IAccountRepository accountRepo;

    IAccountMapper accountMapper;

    SecurityHelper securityHelper;

    //CREATE//
    public AccountRes createOne(AccountCreateReq accountDto) {
        //check that email, phone should be not existed
        if (this.accountRepo.existsByUsername(accountDto.getEmail()))
            throw new AppException(ExceptionCode.ACCOUNT__USERNAME_UNIQUE);
        if (this.accountRepo.existsByEmail(accountDto.getEmail()))
            throw new AppException(ExceptionCode.ACCOUNT__EMAIL_UNIQUE);
        if (this.accountRepo.existsByPhone(accountDto.getPhone()))
            throw new AppException(ExceptionCode.ACCOUNT__PHONE_UNIQUE);
        //prepare entity to created: modify password, role fields
        Account accountCreated = this.accountMapper.toEntity(accountDto);
        accountCreated.setPassword(this.securityHelper.hashPassword(accountDto.getPassword()));
        accountCreated.setRoles(new HashSet<>(Collections.singletonList(RoleEnum.USER.name())));

        Account accountResult = this.accountRepo.save(accountCreated);
        return this.accountMapper.toResponse(accountResult);
    }

    @Override
    public Account preparePreCreate(AccountCreateReq accountDto, RoleEnum roleEnum) {
        Account accountFound = this.accountRepo.findByUsername(accountDto.getUsername()).orElse(null);
        if (Objects.nonNull(accountFound)) {
            //if account is present, throw exception
            if (accountFound.getRoles().contains(RoleEnum.USER.name()))
                throw new AppException(ExceptionCode.ACCESS__USER_NOT_REGISTER_SELLER);
            if (accountFound.getRoles().contains(RoleEnum.SELLER.name()))
                throw new AppException(ExceptionCode.ACCESS__SELLER_NOT_REGISTER_SELLER);
            if (accountFound.getRoles().contains((RoleEnum.ADMIN.name())))
                throw new AppException(ExceptionCode.ACCESS__ADMIN_NOT_REGISTER_SELLER);
        }
        //if account isn't present, create new
        Account accountCreated = Account.builder()
                .username(accountDto.getUsername())
                .password(this.securityHelper.hashPassword(accountDto.getPassword()))
                .roles(new HashSet<>(Collections.singletonList(roleEnum.name())))
                .build();

        return accountCreated;
    }

    //UPDATE//
    public AccountRes updateOne(String accountId, AccountUpdateReq body) {
        //check
        this.findOne(accountId);
        Account account = this.accountMapper.toEntity(body);
        account.setId(accountId);
        account = this.accountRepo.save(account);
        return this.accountMapper.toResponse(account);
    }

    //DELETE//
    public void deleteOne(String accountId) {
        accountRepo.deleteById(accountId);
    }

    //LIST ALL//
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> listAll() {
        List<Account> accounts;
        accounts = this.accountRepo.findAll();
        return accounts;
    }

    //FIND ONE//
    @PostAuthorize("returnObject.accountId == authentication.name")
    public AccountRes findOne(String accountId) {
        Account account = this.accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ExceptionCode.ACCOUNT__NOT_FOUND));
        return this.accountMapper.toResponse(account);
    }

    public AccountRes findMyOne() {
        var context = SecurityContextHolder.getContext();
        String accountId = context.getAuthentication().getName();

        return this.findOne(accountId);
    }
}
