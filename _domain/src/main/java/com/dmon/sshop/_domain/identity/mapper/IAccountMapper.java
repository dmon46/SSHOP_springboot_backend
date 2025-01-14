package com.dmon.sshop._domain.identity.mapper;

import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountMapper {
    Account toEntity(AccountReq.Create dto);
    Account toEntity(AccountReq.Update dto);
    AccountRes toRes(Account entity);
}
