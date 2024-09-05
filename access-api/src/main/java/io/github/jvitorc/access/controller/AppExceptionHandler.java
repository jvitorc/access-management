package io.github.jvitorc.access.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    private final Logger logger = LogManager.getLogger(AppExceptionHandler.class.getName());

    @ExceptionHandler({SecurityException.class})
    public ResponseEntity<String> handleSecurityException(SecurityException e) {
        logger.error(e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
