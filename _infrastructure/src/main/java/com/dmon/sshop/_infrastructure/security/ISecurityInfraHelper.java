package com.dmon.sshop._infrastructure.security;

import com.dmon.sshop._domain.identity.model.entity.Account;

public interface ISecurityInfraHelper {
    //PASSWORD//
    String hashPassword(String plain);
    boolean matchPassword(String plain, String hash);

    //JWT//
    String genToken(Account account);
    String getAccountId();
    String getAccountRole();

}
