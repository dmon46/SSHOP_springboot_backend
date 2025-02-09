package com.dmon.sshop._infrastructure.persistence.repository.identity;

import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.repository.IAccountDomainRepository;
import com.dmon.sshop._infrastructure.persistence.jpa.identity.IAccountJpaMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountInfraRepositoryImpl implements IAccountDomainRepository {
    IAccountJpaMapper accountJpaMapper;

    @Override
    public Optional<Account> findById(String id) {
        return this.accountJpaMapper.findById(id);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return this.accountJpaMapper.findByUsername(username);
    }

    @Override
    public List<Account> findAll() {
        return this.accountJpaMapper.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.accountJpaMapper.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return this.accountJpaMapper.existsByPhone(phone);
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.accountJpaMapper.existsByUsername(username);
    }

    @Override
    public Account save(Account account) {
        return this.accountJpaMapper.save(account);
    }

    @Override
    public void deleteById(String id) {
        this.accountJpaMapper.deleteById(id);
    }
}
