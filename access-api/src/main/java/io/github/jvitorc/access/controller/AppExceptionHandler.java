package io.github.jvitorc.access.controller;

import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    private final Logger logger = LogManager.getLogger(AppExceptionHandler.class.getName());

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> handleValidateException(ConstraintViolationException e) {
        logger.error(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


    @ExceptionHandler({SecurityException.class})
public ResponseEntity<String> handleSecurityException(SecurityException e) {
        logger.error(e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
