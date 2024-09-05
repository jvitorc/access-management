package io.github.jvitorc.access.exception;

public class BusinessExcepton extends RuntimeException {

    public BusinessExcepton(String message) {
        super(message);
    }


    private BusinessExcepton() {
        super(BusinessExcepton.class.getName());
    }
}