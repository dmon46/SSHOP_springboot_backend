package dmon.SSHOP_springboot_backend._controller.account;

import dmon.SSHOP_springboot_backend.dto.request.account.AccountCreateReq;
import dmon.SSHOP_springboot_backend.dto.request.account.AccountUpdateReq;
import dmon.SSHOP_springboot_backend.dto.response.account.AccountRes;
import dmon.SSHOP_springboot_backend.entity.account.Account;
import dmon.SSHOP_springboot_backend._service.account.impl.AccountServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountServiceImpl accountService;

    //CREATE//
    @PostMapping("/create")
    public ResponseEntity<AccountRes> createOne(@Valid @RequestBody AccountCreateReq body){
        return ResponseEntity
                .ok()
                .body(this.accountService.createOne(body));
    }

    //UPDATE//
    @PatchMapping("/update/{accountId}")
    public ResponseEntity<AccountRes> updateOne(@PathVariable String accountId, @RequestBody AccountUpdateReq body) {
        return ResponseEntity
                .ok()
                .body(this.accountService.updateOne(accountId, body));
    }

    //LIST ALL//
    @GetMapping("/list")
    public ResponseEntity<List<Account>> listAll() {
        return ResponseEntity
                .ok()
                .body(this.accountService.listAll());
    }

    //FIND ONE//
    @GetMapping("/find/{accountId}")
    public ResponseEntity<AccountRes> findOne(@PathVariable String accountId)  {
        return ResponseEntity
                .ok()
                .body(this.accountService.findOne(accountId));
    }

    @GetMapping("/my")
    public ResponseEntity<AccountRes> findMyOne()  {
        return ResponseEntity
                .ok()
                .body(this.accountService.findMyOne());
    }

    //DELETE//
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable("id") String accountId) {
        this.accountService.deleteOne(accountId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
