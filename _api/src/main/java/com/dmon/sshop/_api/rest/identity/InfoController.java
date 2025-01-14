package com.dmon.sshop._api.rest.identity;

import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.request.UserReq;
import com.dmon.sshop._domain.identity.model.response.UserRes;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class InfoController {
    //SELLER//
    @RestController
    @RequestMapping("/seller/api/v1/info")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class InfoSellerController {
        //VERIFY//
        @PostMapping("/verify-shop")
        public ResponseEntity<AccountRes.Access> verify(
                @RequestBody Object request
        ) {
            return ResponseEntity
                    .ok()
                    .body(null);
        }

    }

    //BUYER//
    @RestController
    @RequestMapping("/api/v1/info")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class InforUserController {
        @PostMapping("/create")
        public ResponseEntity<UserRes> createOne(
                @Valid @RequestBody UserReq.Create body
        ){
            return ResponseEntity
                    .ok()
                    .body(null);
        }
    }

}
