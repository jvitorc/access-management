package io.github.jvitorc.access.service;

import io.github.jvitorc.access.dto.AuthRegister;
import io.github.jvitorc.access.dto.AuthRequest;
import io.github.jvitorc.access.dto.AuthResponse;
import io.github.jvitorc.access.exception.IllegalArgumentSecurityException;
import io.github.jvitorc.access.exception.InvalidPasswordException;
import io.github.jvitorc.access.exception.UserNotFoundException;
import io.github.jvitorc.access.jwt.JwtUtil;
import io.github.jvitorc.access.model.AccessToken;
import io.github.jvitorc.access.model.Account;
import io.github.jvitorc.access.validator.BasicValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private final Logger logger = LogManager.getLogger(AuthenticationService.class.getName());

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final BasicValidator validator;
    private final AccessTokenService accessTokenService;

    @Transactional
    public AuthResponse authenticate(AuthRequest request) {

        if (isNull(request)) {
            throw new IllegalArgumentSecurityException();
        }
        validator.validate(request);

        Account account = accountService.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new InvalidPasswordException();
        }

        String accessToken = JwtUtil.generateToken(new HashMap<>(), account);

        accessTokenService.revokeAllByAccount(account);
        accessTokenService.save(accessToken, account);

        return new AuthResponse(accessToken, "");
    }

    @Transactional
    public AuthResponse register(AuthRegister register) {
        validator.validate(register);

        String password = passwordEncoder.encode(register.getPassword());
        Account account = Account.builder()
                .name(register.getName())
                .password(password)
                .email(register.getEmail()).build();

        account = accountService.create(account);

        String accessToken = JwtUtil.generateToken(new HashMap<>(), account);
        accessTokenService.save(accessToken, account);

        return new AuthResponse(accessToken, "");
    }

    @Transactional
    public AuthResponse refresh(String bearerToken) {
        Optional<AccessToken> token = accessTokenService.findActiveToken(bearerToken);
        if (token.isEmpty()) {
            throw new IllegalArgumentSecurityException();
        }

        Account account = token.get().getAccount();

        String refreshToken = JwtUtil.generateToken(new HashMap<>(), account);
        accessTokenService.revokeAllByAccount(account);
        accessTokenService.save(refreshToken, account);

        return new AuthResponse(bearerToken,refreshToken);
    }
}
