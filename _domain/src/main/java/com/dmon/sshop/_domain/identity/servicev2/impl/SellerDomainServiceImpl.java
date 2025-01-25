package com.dmon.sshop._domain.identity.servicev2.impl;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.entity.Seller;
import com.dmon.sshop._domain.identity.repository.ISellerDomainRepository;
import com.dmon.sshop._domain.identity.servicev2.ISellerDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class SellerDomainServiceImpl implements ISellerDomainService {

    ISellerDomainRepository sellerDomainRepo;


    @Override
    public Seller findOrError(String sellerId) {
        Seller sellerPresent = this.sellerDomainRepo.findById(sellerId)
                .orElseThrow(() -> new AppException(ErrorCode.SELLER__NOT_FOUND));

        return sellerPresent;
    }
}
