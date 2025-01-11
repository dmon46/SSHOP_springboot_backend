package dmon.SSHOP_springboot_backend.mapper.account;

import org.mapstruct.Mapper;

import dmon.SSHOP_springboot_backend.dto.request.account.AccountCreateReq;
import dmon.SSHOP_springboot_backend.dto.request.account.AccountUpdateReq;
import dmon.SSHOP_springboot_backend.dto.response.account.AccountRes;
import dmon.SSHOP_springboot_backend.entity.account.Account;

@Mapper(componentModel = "spring")
public interface IAccountMapper {
    Account toEntity(AccountCreateReq dto);

    Account toEntity(AccountUpdateReq dto);

    AccountRes toResponse(Account entity);
}
