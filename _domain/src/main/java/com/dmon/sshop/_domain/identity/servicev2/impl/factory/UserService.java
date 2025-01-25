package com.dmon.sshop._domain.identity.servicev2.impl.factory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements IAccessService, IAccountService {
    @Override
    public Object login(Object AccountLoginReq) {
        return null;
    }

    @Override
    public Object logout() {
        return null;
    }

    @Override
    public Object signup(Object AccountSignupReq) {
        return null;
    }

    @Override
    public Object forgotPassword() {
        return null;
    }

    @Override
    public Object getAccount() {
        return null;
    }

    @Override
    public Object getProfile() {
        return null;
    }
}
