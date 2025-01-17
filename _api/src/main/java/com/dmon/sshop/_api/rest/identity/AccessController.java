package com.dmon.sshop._api.rest.identity;

import com.dmon.sshop._application.service.identity.IAccessAppService;
import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class AccessController {
    //ADMIN//
    @RestController
    @RequestMapping("/admin/api/v1/access")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class AccessAdminController {
        IAccessAppService accessAppService;

        //LOG IN, OUT//
        @PostMapping("/login")
        public ResponseEntity<AccountRes.Access> login(
                @RequestBody AccountReq.Login request
        ) {
            return ResponseEntity
                    .ok()
                    .body(this.accessAppService.login(request, Account.RoleType.ADMIN));
        }

        @PostMapping("/logout")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<Void> logout() {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(this.accessAppService.logout());
        }
    }

    //SELLER//
    @RestController
    @RequestMapping("/seller/api/v1/access")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class AccessSellerController {

        IAccessAppService accessAppService;
        Account.RoleType roleType = Account.RoleType.SELLER;

        //LOG IN, OUT//
        @PostMapping("/login")
        public ResponseEntity<AccountRes.Access> login(
                @RequestBody AccountReq.Login accountLoginReq
        ) {
            //todo: (side): don't allow seller login when status is registering (and others)
            return ResponseEntity
                    .ok()
                    .body(this.accessAppService.login(accountLoginReq, roleType));
        }

        @PostMapping("/logout")
        @PreAuthorize("hasRole('SELLER')")
        public ResponseEntity<Void> logout() {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(this.accessAppService.logout());
        }

        //SIGN UP//
        @PostMapping("/signup")
        public ResponseEntity<Object> signup(
                @RequestBody AccountReq.Create accessDto
        ) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.accessAppService.signup(accessDto, roleType));
        }
    }

    //BUYER//
    @RestController
    @RequestMapping("/api/v1/access")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class AccessUserController {

        IAccessAppService accessAppService;
        Account.RoleType roleType = Account.RoleType.USER;

        //LOG IN, OUT//
        @PostMapping("/login")
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<AccountRes.Access> login(
                @RequestBody AccountReq.Login accountLoginReq
        ) {
            return ResponseEntity
                    .ok()
                    .body(this.accessAppService.login(accountLoginReq, roleType));
        }

        @PostMapping("/logout")
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<Void> logout() {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(this.accessAppService.logout());
        }

        //SIGN UP//
        @PostMapping("/signup")
        public ResponseEntity<Object> signup(
                @RequestBody AccountReq.Create accessCreateReq
        ) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.accessAppService.signup(accessCreateReq, roleType));
        }
    }
}
