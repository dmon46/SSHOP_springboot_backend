package com.dmon.sshop._domain.identity.repository;

import java.util.Optional;

import com.dmon.sshop._domain.identity.model.entity.Seller;

public interface ISellerDomainRepository {
    // FIND//
    Optional<Seller> findById(String id);

    // PERSIST//
    Seller save(Seller seller);
}
