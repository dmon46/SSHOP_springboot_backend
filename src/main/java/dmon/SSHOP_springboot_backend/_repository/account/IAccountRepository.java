package dmon.SSHOP_springboot_backend._repository.account;

import dmon.SSHOP_springboot_backend.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String> {
    //FIND//
    Optional<Account> findByUsername(String s);

    //EXIST//
    boolean existsByEmail(String s);
    boolean existsByPhone(String s);
    boolean existsByUsername(String s);
}
