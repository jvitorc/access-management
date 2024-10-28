package io.github.jvitorc.access.controller;

import io.github.jvitorc.access.dto.AccountDTO;
import io.github.jvitorc.access.dto.AccountUpdateDTO;
import io.github.jvitorc.access.dto.AccountUpdatePasswordDTO;
import io.github.jvitorc.access.exception.BusinessException;
import io.github.jvitorc.access.model.Account;
import io.github.jvitorc.access.dto.AccountUpdateProfileDTO;
import io.github.jvitorc.access.service.AccountService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static io.github.jvitorc.access.util.RequestUtil.pageRequestBuild;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private AccountService accountService;

    @GetMapping
    public ResponseEntity<Page<Account>> findAll(@PathParam("pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(accountService.findAll(pageRequestBuild(pageNumber)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PatchMapping("/{id}/profile")
    public ResponseEntity<Account> updateProfile(@PathVariable Integer id, @RequestBody AccountUpdateProfileDTO body) {
        if (!Objects.equals(id, body.getId())) {
            throw new BusinessException("ids do not match");
        }

        accountService.updateProfile(body);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Integer id, @RequestBody AccountUpdateDTO body) {
        if (!Objects.equals(id, body.getId())) {
            throw new BusinessException("ids do not match");
        }
        accountService.update(body);
        return ResponseEntity.accepted().build();
    }

}
