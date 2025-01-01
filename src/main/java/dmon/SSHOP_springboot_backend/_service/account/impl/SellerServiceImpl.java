package dmon.SSHOP_springboot_backend._service.account.impl;

import dmon.SSHOP_springboot_backend._repository.account.ISellerRepository;
import dmon.SSHOP_springboot_backend._service.account.IAccessFactory;
import dmon.SSHOP_springboot_backend._service.account.ISellerHelper;
import dmon.SSHOP_springboot_backend.base.AppException;
import dmon.SSHOP_springboot_backend.base.ExceptionCode;
import dmon.SSHOP_springboot_backend.dto.request.account.AccountCreateReq;
import dmon.SSHOP_springboot_backend.dto.response.account.SignupRes;
import dmon.SSHOP_springboot_backend.entity.account.Account;
import dmon.SSHOP_springboot_backend.entity.account.Seller;
import dmon.SSHOP_springboot_backend.enums.RoleEnum;
import dmon.SSHOP_springboot_backend.enums.StatusEnum;
import dmon.SSHOP_springboot_backend.mapper.account.IAccessMapper;
import dmon.SSHOP_springboot_backend.security.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SellerServiceImpl implements IAccessFactory, ISellerHelper {
    ISellerRepository sellerRepo;

    IAccessMapper accessMapper;

    AccountServiceImpl accountService;

    //FACTORY//
    @Override
    public SignupRes create(AccountCreateReq accountDto) {
        Account accountCreated = this.accountService
                .preparePreCreate(accountDto, RoleEnum.SELLER);
        Seller sellerCreated = Seller.builder()
                .account(accountCreated)
                .status(StatusEnum.Seller.REGISTERING.name())
                .build();

        Seller sellerResult = this.sellerRepo.save(sellerCreated);
        return this.accessMapper.toRes(sellerResult);
    }

    //HELPER//
    @Override
    public Seller findCurrentLogin() {
        String accountId = SecurityUtil.getCurrentAccount()
                .orElseThrow(() -> new AppException(ExceptionCode.ACCESS__NOT_IN_SECURITY_CONTEXT));
        Seller sellerPresent = this.sellerRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ExceptionCode.ACCESS__NOT_IN_SECURITY_CONTEXT));
        return sellerPresent;
    }
}
