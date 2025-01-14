package com.dmon.sshop._domain.identity.repository;

import com.dmon.sshop._domain.identity.model.entity.User;

public interface IUserDomainRepository {
    // PERSIST//
    User save(User user);
}
