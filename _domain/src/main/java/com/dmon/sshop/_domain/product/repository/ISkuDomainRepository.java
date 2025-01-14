package com.dmon.sshop._domain.product.repository;

import java.util.Optional;

import com.dmon.sshop._domain.inventory.model.entity.Sku;

public interface ISkuDomainRepository {
    // FIND//
    Optional<Sku> findBySkuCode(String skuCode);
}
