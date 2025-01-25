package com.dmon.sshop._domain.identity.servicev2.impl.factory;

public interface IAccessService {
    Object login(Object AccountLoginReq);
    Object logout();
    Object signup(Object AccountSignupReq);
    Object forgotPassword();
}
