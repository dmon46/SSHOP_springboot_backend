package dmon.SSHOP_springboot_backend._service.account.impl;

import dmon.SSHOP_springboot_backend.dto.request.account.UserCreateReq;
import org.springframework.stereotype.Service;

import dmon.SSHOP_springboot_backend._repository.account.IUserRepository;
import dmon.SSHOP_springboot_backend.dto.response.account.UserRes;
import dmon.SSHOP_springboot_backend.entity.account.User;
import dmon.SSHOP_springboot_backend.mapper.account.IUserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl {
    IUserRepository userRepo;
    IUserMapper userMapper;

    public UserRes create(UserCreateReq body) {
        // todo: refer to account
        User user = this.userRepo.save(
                this.userMapper.toEntity(body));
        return this.userMapper.toResponse(user);
    }
}
