package com.dmon.sshop._domain.product.service;

public interface ISkuDomainService {
    //SERVICES//

    /**
     * @param skuCode String
     * @desc check if the value of field is unique.
     */
    void checkSkuCodeOrError(String skuCode);
}
