package com.dmon.sshop._infrastructure.persistence.repository.identity;

import com.dmon.sshop._domain.identity.model.entity.User;
import com.dmon.sshop._domain.identity.repository.IUserDomainRepository;
import com.dmon.sshop._infrastructure.persistence.jpa.identity.IUserJpaMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserInfraRepositoryImpl implements IUserDomainRepository {
    IUserJpaMapper userJpaMapper;

    @Override
    public User save(User user) {
        return this.userJpaMapper.save(user);
    }
}
