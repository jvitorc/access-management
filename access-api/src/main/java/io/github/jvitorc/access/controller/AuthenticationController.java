package io.github.jvitorc.access.controller;

import io.github.jvitorc.access.dto.AuthRegister;
import io.github.jvitorc.access.dto.AuthRequest;
import io.github.jvitorc.access.dto.AuthResponse;
import io.github.jvitorc.access.model.Account;
import io.github.jvitorc.access.service.AuthenticationService;
import io.github.jvitorc.access.util.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {


    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRegister account) {
        return ResponseEntity.ok(service.register(account));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refresh(HttpServletRequest request) {
        return ResponseEntity.ok(service.refresh(RequestUtil.extractBearerToken(request)));
    }
}
