package com.dmon.sshop._infrastructure.persistence.jpa.identity;

import com.dmon.sshop._domain.identity.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserJpaMapper extends JpaRepository<User, String> {
}
