package com.dmon.sshop._infrastructure.config;

import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.repository.IAccountDomainRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AppInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(IAccountDomainRepository accountRepo) {
        return args -> {
            if (accountRepo.findByUsername("admin").isEmpty()) {
                HashSet<String> roles = new HashSet<>();
                roles.add(Account.RoleEnum.ADMIN.name());

                Account account = Account.builder()
                        .username("admin")
                        .email("admin@sshop.com")
                        .password(passwordEncoder.encode("123456"))
                        .roles(roles)
                        .build();

                accountRepo.save(account);
                log.warn("An admin account have been created with password 123456.Please change it !");
            }
        };
    }
}
