package com.dmon.sshop._domain.identity.mapper;

import com.dmon.sshop._domain.identity.model.request.UserReq;
import com.dmon.sshop._domain.identity.model.response.UserRes;
import com.dmon.sshop._domain.identity.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    User toEntity(UserReq.Create dto);
    UserRes toRes(User entity);
}
