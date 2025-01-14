package com.dmon.sshop._domain.identity.repository;

import java.util.List;
import java.util.Optional;

import com.dmon.sshop._domain.identity.model.entity.Account;

public interface IAccountDomainRepository {
    // FIND//
    Optional<Account> findById(String id);

    Optional<Account> findByUsername(String username);

    List<Account> findAll();

    // EXIST//
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByUsername(String username);

    // PERSIST//
    Account save(Account account);

    void deleteById(String id);
}
