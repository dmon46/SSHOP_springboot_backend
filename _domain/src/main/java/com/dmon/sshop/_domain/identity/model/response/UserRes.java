package com.dmon.sshop._domain.identity.model.response;

import com.dmon.sshop._domain.identity.model.entity.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRes {
    String id;
    String name;
    String photo;
    Date dob;
    Account.GenderType gender;
}
