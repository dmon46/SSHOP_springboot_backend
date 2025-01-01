package dmon.SSHOP_springboot_backend.mapper.account;

import dmon.SSHOP_springboot_backend.dto.response.account.SignupRes;
import dmon.SSHOP_springboot_backend.entity.account.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccessMapper {
    SignupRes toRes(Seller entity);
}
