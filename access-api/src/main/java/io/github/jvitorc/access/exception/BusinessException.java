package io.github.jvitorc.access.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }


    private BusinessException() {
        super(BusinessException.class.getName());
    }
}