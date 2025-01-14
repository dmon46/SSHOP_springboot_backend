package com.dmon.sshop._domain.identity.mapper;

import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccessMapper {
    AccountRes.Signup toRes(Seller entity);
}
