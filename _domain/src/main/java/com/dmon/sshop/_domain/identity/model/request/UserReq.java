package com.dmon.sshop._domain.identity.model.request;

import com.dmon.sshop._domain.identity.model.entity.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

public class UserReq {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Create {
        String name;
        String photo;
        Date dob;
        Account.GenderType gender;
    }
}
