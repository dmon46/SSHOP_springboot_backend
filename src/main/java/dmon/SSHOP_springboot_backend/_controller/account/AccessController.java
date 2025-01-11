package dmon.SSHOP_springboot_backend._controller.account;

import dmon.SSHOP_springboot_backend._service.account.IAccessService;
import dmon.SSHOP_springboot_backend.dto.request.account.AccountCreateReq;
import dmon.SSHOP_springboot_backend.dto.request.account.LoginReq;
import dmon.SSHOP_springboot_backend.dto.response.account.AccessRes;
import dmon.SSHOP_springboot_backend.dto.response.account.SignupRes;
import dmon.SSHOP_springboot_backend.enums.RoleEnum;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class AccessController {
    @RestController
    @RequestMapping("/admin/api/v1/access")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Admin {
        IAccessService accessService;

        //LOG IN, OUT//
        @PostMapping("/login")
        public ResponseEntity<AccessRes> login(@RequestBody LoginReq request) {
            return ResponseEntity
                    .ok()
                    .body(this.accessService.login(request, RoleEnum.ADMIN));
        }

        @PostMapping("/logout")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<Void> logout() {
            this.accessService.logout();
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }

    @RestController
    @RequestMapping("/seller/api/v1/access")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Seller {
        IAccessService accessService;

        //LOG IN, OUT//
        @PostMapping("/login")
        public ResponseEntity<AccessRes> login(@RequestBody LoginReq request) {
            //todo: (side): don't allow seller login when status is registering (and others)
            return ResponseEntity
                    .ok()
                    .body(this.accessService.login(request, RoleEnum.SELLER));
        }

        @PostMapping("/logout")
        @PreAuthorize("hasRole('SELLER')")
        public ResponseEntity<Void> logout() {
            this.accessService.logout();
            return ResponseEntity
                    .noContent()
                    .build();
        }

        //SIGN UP//
        @PostMapping("/signup")
        public ResponseEntity<SignupRes> signup(@RequestBody AccountCreateReq accessDto) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.accessService.signup(accessDto, RoleEnum.SELLER));
        }
    }

    @RestController
    @RequestMapping("/api/v1/access")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Buyer {
        IAccessService accessService;

        //LOG IN, OUT//
        @PostMapping("/login")
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<AccessRes> login(@RequestBody LoginReq request) {
            return ResponseEntity
                    .ok()
                    .body(this.accessService.login(request, RoleEnum.USER));
        }
    }
}
