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
import io.github.jvitorc.access.model.AccountDetails;
import io.github.jvitorc.access.repository.AccountDetailsRepository;
import io.github.jvitorc.access.validator.BasicValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static io.github.jvitorc.access.data.AuthenticationDataTest.accountTest;
import static io.github.jvitorc.access.data.AuthenticationDataTest.authRegisterTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private BasicValidator validator;

    @Mock
    private AccessTokenService accessTokenService;

    @Mock
    private AccountDetailsRepository accountDetailsRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Account account = accountTest();
        AccountDetails accountDetails = AccountDetails.builder()
                .id(account.getId()).email(account.getEmail())
                .password(account.getPassword()).build();

        when(accountDetailsRepository.findByEmail(account.getEmail()))
                .thenReturn(Optional.of(accountDetails));

        when(accountService.create(any()))
                .thenAnswer(o -> o.getArgument(0, Account.class));

        PasswordEncoder pe = new BCryptPasswordEncoder();

        when(passwordEncoder.matches(anyString(), anyString()))
                .thenAnswer(o -> pe.matches(o.getArgument(0, String.class), o.getArgument(1, String.class)));

        when(accessTokenService.findActiveToken(anyString()))
                .thenAnswer(o -> Optional.of(AccessToken.builder().token(o.getArgument(0)).account(accountTest()).build()));

    }

    @Test
    public void testLogin_NullRequest_ThrowsIllegalArgumentSecurityException() {

        assertThrows(IllegalArgumentSecurityException.class, () -> authenticationService.login(null));
    }

    @Test
    public void testLogin_UserNotFound() {

        AuthRequest request = new AuthRequest("incorrect", "");
        assertThrows(UserNotFoundException.class, () -> authenticationService.login(request));
    }

    @Test
    public void testLogin_InvalidPassword() {
        Account account = accountTest();

        AuthRequest request = new AuthRequest(account.getEmail(), "incorrect");
        assertThrows(InvalidPasswordException.class, () -> authenticationService.login(request));
    }

    @Test
    public void testLogin_success() {
        Account account = accountTest();
        AuthRequest request = new AuthRequest(account.getEmail(), "password");

        AuthResponse authenticate = authenticationService.login(request);

        String subject = JwtUtil.extractAllClaims(authenticate.accessToken()).getSubject();
        assertEquals(subject, account.getEmail());


        // TODO: Test: Validator
        Mockito.verify(validator, times(1)).validate(any());

        // TODO: Test: AccessTokenService
        Mockito.verify(accessTokenService, times(1)).revokeAllByAccount(any());

        // TODO: Test: AccessTokenService
        Mockito.verify(accessTokenService, times(1)).save(any(), any());
    }

    @Test
    public void testRegister_success() {
        AuthRegister authRegister = authRegisterTest();

        AuthResponse response = authenticationService.register(authRegister);

        String subject = JwtUtil.extractAllClaims(response.accessToken()).getSubject();
        assertEquals(subject, authRegister.getEmail());

        // TODO: Test: Validator
        Mockito.verify(validator, times(1)).validate(any());
        // TODO: Test: AccessTokenService
        Mockito.verify(accountService, times(1)).create(any());
        // TODO: Test: AccessTokenService
        Mockito.verify(passwordEncoder, times(1)).encode(authRegister.getPassword());
    }


    @Test
    public void testRefresh_TokenNull() {
        when(accessTokenService.findActiveToken(anyString()))
                .thenReturn(Optional.empty());


        assertThrows(IllegalArgumentSecurityException.class, () -> authenticationService.login(null));
    }

    @Test
    public void testRefresh_TokenNotFound() {
        when(accessTokenService.findActiveToken(anyString()))
                .thenReturn(Optional.empty());

        String invalidToken = "invalid";

        assertThrows(IllegalArgumentSecurityException.class, () -> authenticationService.refresh(invalidToken));
    }



    @Test
    public void testRefresh_success() {

        Account account = accountTest();
        AuthRequest request = new AuthRequest(account.getEmail(), "password");
        AuthResponse authResponse = authenticationService.login(request);

        AuthResponse refreshResponse = authenticationService.refresh(authResponse.accessToken());

        assertEquals(authResponse.accessToken(), refreshResponse.accessToken());

        String authSubject = JwtUtil.extractAllClaims(refreshResponse.refreshToken()).getSubject();
        String refreshSubject = JwtUtil.extractAllClaims(refreshResponse.refreshToken()).getSubject();
        assertEquals(authSubject, refreshSubject);
    }


}