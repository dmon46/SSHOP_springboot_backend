package dmon.SSHOP_springboot_backend._controller.account;

import dmon.SSHOP_springboot_backend.dto.request.account.LoginReq;
import dmon.SSHOP_springboot_backend.dto.response.account.AccessRes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller/api/v1/info")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SellerController {


    //VERIFY//
    @PostMapping("/verify-shop")
    public ResponseEntity<AccessRes> verify(@RequestBody Object request) {
        return ResponseEntity
                .ok()
                .body(null);
    }
}
