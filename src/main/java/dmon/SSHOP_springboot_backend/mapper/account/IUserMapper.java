package dmon.SSHOP_springboot_backend.mapper.account;

import dmon.SSHOP_springboot_backend.dto.request.account.UserCreateReq;
import dmon.SSHOP_springboot_backend.dto.response.account.UserRes;
import org.mapstruct.Mapper;

import dmon.SSHOP_springboot_backend.entity.account.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    User toEntity(UserCreateReq dto);

    UserRes toResponse(User entity);
}
