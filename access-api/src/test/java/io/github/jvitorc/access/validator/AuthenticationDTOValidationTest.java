package io.github.jvitorc.access.validator;

import io.github.jvitorc.access.dto.AuthRegister;
import io.github.jvitorc.access.dto.AuthRequest;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.github.jvitorc.access.data.AuthenticationDataTest.authRegisterTest;
import static io.github.jvitorc.access.data.AuthenticationDataTest.authRequestTest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AuthenticationDTOValidationTest {

    @Autowired
    BasicValidator basicValidator;

    @Test
    public void AuthRequest_Email_Invalid() {
        AuthRequest authRequest = authRequestTest();
        authRequest.setEmail("1234");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRequest));;
    }

    @Test
    public void AuthRequest_Email_NotBlank() {
        AuthRequest authRequest = authRequestTest();
        authRequest.setEmail("");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRequest));;
    }

    @Test
    public void AuthRequest_Password_NotBlank() {
        AuthRequest authRequest = authRequestTest();
        authRequest.setPassword("");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRequest));;
    }

    @Test
    public void AuthRequest_success() {
        AuthRequest authRequest = authRequestTest();

        assertTrue(basicValidator.validate(authRequest));
    }

    @Test
    public void AuthRegister_Name_NotBlank() {
        AuthRegister authRegister = authRegisterTest();
        authRegister.setName("");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRegister));;
    }

    @Test
    public void AuthRegister_Name_MinThree() {
        AuthRegister authRegister = authRegisterTest();
        authRegister.setName("12");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRegister));;
    }

    @Test
    public void AuthRegister_Email_NotBlank() {
        AuthRegister authRegister = authRegisterTest();
        authRegister.setEmail("");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRegister));;
    }

    @Test
    public void AuthRegister_Email_Invalid() {
        AuthRegister authRegister = authRegisterTest();
        authRegister.setEmail("1234");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRegister));;
    }

    @Test
    public void AuthRegister_Password_NotBlank() {
        AuthRegister authRegister = authRegisterTest();
        authRegister.setPassword("");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRegister));;
    }

    @Test
    public void AuthRegister_Password_HasLowerCase() {
        AuthRegister authRegister = authRegisterTest();
        authRegister.setPassword("WORD1234@@@@");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRegister));;
    }

    @Test
    public void AuthRegister_Password_HasSpecialCharacter() {
        AuthRegister authRegister = authRegisterTest();
        authRegister.setPassword("passWORD1234");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRegister));;
    }

    @Test
    public void AuthRegister_Password_HasUpperCase() {
        AuthRegister authRegister = authRegisterTest();
        authRegister.setPassword("pass1234@@@@");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRegister));;
    }

    @Test
    public void AuthRegister_Password_HasDigit() {
        AuthRegister authRegister = authRegisterTest();
        authRegister.setPassword("passWORD@@@@");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRegister));;
    }

    @Test
    public void AuthRegister_Password_MinEight() {
        AuthRegister authRegister = authRegisterTest();
        authRegister.setPassword("pW@4567");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRegister));;
    }

    @Test
    public void AuthRegister_Password_MaxEighteen() {
        AuthRegister authRegister = authRegisterTest();
        authRegister.setPassword("pW@45678pW@45678p");

        assertThrows(ConstraintViolationException.class, () -> basicValidator.validate(authRegister));;
    }

    @Test
    public void AuthRegister_Password_success() {
        AuthRegister authRegister = authRegisterTest();

        assertTrue(basicValidator.validate(authRegister));;
    }





}
