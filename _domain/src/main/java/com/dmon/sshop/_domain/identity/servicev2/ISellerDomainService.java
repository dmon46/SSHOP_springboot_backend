package com.dmon.sshop._domain.identity.servicev2;

import com.dmon.sshop._domain.identity.model.entity.Shop;

public interface ISellerDomainService {
    //HELPER//
    Shop findOrError(String sellerId);
}
