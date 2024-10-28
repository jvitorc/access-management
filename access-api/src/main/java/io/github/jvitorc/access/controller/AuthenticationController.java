package io.github.jvitorc.access.controller;

import io.github.jvitorc.access.dto.*;
import io.github.jvitorc.access.exception.BusinessException;
import io.github.jvitorc.access.model.Account;
import io.github.jvitorc.access.repository.ChangePasswordRepository;
import io.github.jvitorc.access.service.AuthenticationService;
import io.github.jvitorc.access.util.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {


    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRegister account) {
        return ResponseEntity.ok(service.register(account));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refresh(HttpServletRequest request) {
        return ResponseEntity.ok(service.refresh(RequestUtil.extractBearerToken(request)));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        service.logout(RequestUtil.extractBearerToken(request));
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/password/update/{urlSecret}")
    public ResponseEntity<Account> updatePassword(@PathVariable String urlSecret, @RequestBody AccountUpdatePasswordDTO body) {
        service.updatePassword(urlSecret,body);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/password/url")
    public ResponseEntity<String>  getUrlChangePassword(@RequestBody AuthCreateUrlPasswordDTO body) {
        // TODO: SEND BY EMAIL
        return ResponseEntity.ok(service.createUrlChangePassword(body));
    }

}
