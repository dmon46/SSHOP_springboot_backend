package com.dmon.sshop._domain.identity.servicev2;

import com.dmon.sshop._domain.identity.model.entity.Seller;

public interface ISellerDomainService {
    //HELPER//
    Seller findOrError(String sellerId);
}
