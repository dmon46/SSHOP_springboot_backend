package com.dmon.sshop._domain.identity.service.impl;

import com.dmon.sshop._domain.identity.model.request.UserReq;
import com.dmon.sshop._domain.identity.model.response.UserRes;
import com.dmon.sshop._domain.identity.mapper.IUserMapper;
import com.dmon.sshop._domain.identity.model.entity.User;
import com.dmon.sshop._domain.identity.repository.IUserDomainRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

//@Service
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class UserDomainServiceImpl {
//    IUserDomainRepository userRepo;
//
//    IUserMapper userMapper;
//
//    public UserRes create(UserReq.Create body) {
//        // todo: refer to account
//        User user = this.userRepo.save(
//                this.userMapper.toEntity(body));
//        return this.userMapper.toRes(user);
//    }
//}
