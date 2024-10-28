package io.github.jvitorc.access.service;

import io.github.jvitorc.access.dto.*;
import io.github.jvitorc.access.exception.BusinessException;
import io.github.jvitorc.access.exception.IllegalArgumentSecurityException;
import io.github.jvitorc.access.exception.InvalidPasswordException;
import io.github.jvitorc.access.exception.UserNotFoundException;
import io.github.jvitorc.access.jwt.JwtUtil;
import io.github.jvitorc.access.model.AccessToken;
import io.github.jvitorc.access.model.Account;
import io.github.jvitorc.access.model.AccountDetails;
import io.github.jvitorc.access.model.ChangePassword;
import io.github.jvitorc.access.repository.AccountDetailsRepository;
import io.github.jvitorc.access.repository.AccountRepository;
import io.github.jvitorc.access.repository.ChangePasswordRepository;
import io.github.jvitorc.access.validator.BasicValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private final Logger logger = LogManager.getLogger(AuthenticationService.class.getName());

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final BasicValidator validator;
    private final AccessTokenService accessTokenService;
    private final AccountDetailsRepository accountDetailsRepository;
    private final ChangePasswordRepository changePasswordRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public AuthResponse login(AuthRequest request) {

        if (isNull(request)) {
            throw new IllegalArgumentSecurityException();
        }
        validator.validate(request);

        AccountDetails account = accountDetailsRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);

        Account acc = Account.builder().id(account.getId()).build();

        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new InvalidPasswordException();
        }

        String accessToken = JwtUtil.generateToken(new HashMap<>(), account.getUsername());

        accessTokenService.revokeAllByAccount(acc);
        accessTokenService.save(accessToken, acc);

        return new AuthResponse(accessToken, account.getName(), account.getEmail(), "");
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

        String accessToken = JwtUtil.generateToken(new HashMap<>(), account.getEmail());
        accessTokenService.save(accessToken, account);

        return new AuthResponse(accessToken, account.getName(), account.getEmail(), "");
    }

    @Transactional
    public AuthResponse refresh(String bearerToken) {
        Optional<AccessToken> token = accessTokenService.findActiveToken(bearerToken);
        if (token.isEmpty()) {
            throw new IllegalArgumentSecurityException();
        }

        Account account = token.get().getAccount();

        String refreshToken = JwtUtil.generateToken(new HashMap<>(), account.getEmail());
        accessTokenService.revokeAllByAccount(account);
        accessTokenService.save(refreshToken, account);

        return new AuthResponse(bearerToken,account.getName(), account.getEmail(), refreshToken);
    }

    public void logout(String accessToken) {
        Optional<AccessToken> token = accessTokenService.findActiveToken(accessToken);
        if (token.isEmpty()) {
            throw new IllegalArgumentSecurityException();
        }

        Account account = token.get().getAccount();
        accessTokenService.revokeAllByAccount(account);
    }

    public void updatePassword(String urlSecret, AccountUpdatePasswordDTO body) {

        ChangePassword change = changePasswordRepository.findByUrlSecret(urlSecret)
                .orElseThrow(IllegalArgumentSecurityException::new);

        if (FALSE.equals(change.getStatus())) {
            throw new IllegalArgumentSecurityException();
        }

        Instant now = Instant.now(); //current date
        Instant expire = now.plus(Duration.ofHours(1));
        if (expire.isBefore(change.getCreateDate().toInstant())) {
            throw new IllegalArgumentSecurityException();
        }

        validator.validate(body);

        if (!body.getPassword().equals(body.getConfirmPassword())) {
            throw new BusinessException("passwords do not match");
        }

        if (passwordEncoder.matches(body.getPassword(), change.getOldPassword())) {
            throw new InvalidPasswordException();
        }

        change.setStatus(true);
        changePasswordRepository.save(change);

        String password = passwordEncoder.encode(body.getPassword());

        Account account = change.getAccount();
        account.setPassword(password);
        accountRepository.save(account);
    }

    public String createUrlChangePassword(AuthCreateUrlPasswordDTO dto) {

        try {
            validator.validate(dto);

            Account account = accountRepository.findByEmail(dto.getEmail())
                    .orElseThrow(UserNotFoundException::new);

            ChangePassword build = ChangePassword.builder()
                    .createDate(new Date()).oldPassword(account.getPassword()).status(true)
                    .urlSecret(UUID.randomUUID().toString()).account(account).build();

            changePasswordRepository.save(build);

            // TODO: ADD HOST
            return "password/update/" + build.getUrlSecret();

        } catch (Exception e) {
            return "Url envianda por email " + dto.getEmail();
        }
    }
}
