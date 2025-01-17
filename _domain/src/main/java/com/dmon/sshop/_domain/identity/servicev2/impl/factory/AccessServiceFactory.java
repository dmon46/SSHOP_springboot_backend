package com.dmon.sshop._domain.identity.servicev2.impl.factory;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.entity.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccessServiceFactory {

    UserService userService;
    SellerService sellerService;

    public IAccessService get(Account.RoleType roleType) {
        return switch (roleType) {
            case USER -> userService;
            case SELLER -> sellerService;
            default -> throw new AppException(ErrorCode.SYSTEM__ENUM_KEY_INVALID);
        };
    }

}
