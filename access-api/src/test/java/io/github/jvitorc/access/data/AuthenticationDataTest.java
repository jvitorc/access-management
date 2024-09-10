package io.github.jvitorc.access.data;

import io.github.jvitorc.access.dto.AuthRegister;
import io.github.jvitorc.access.dto.AuthRequest;
import io.github.jvitorc.access.model.Account;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationDataTest {


    public static Account accountTest() {
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode("password");

        return  Account.builder().name("Teste")
                .email("test@example.com")
                .password(password).build();
    }

    public static AuthRegister authRegisterTest() {
        return AuthRegister.builder()
                .name("Teste")
                .email("test@example.com")
                .password("passWORD1234@@@@")
                .build();
    }

    public static AuthRequest authRequestTest() {
        return AuthRequest.builder()
                .email("test@email.com")
                .password("1234")
                .build();
    }
}
