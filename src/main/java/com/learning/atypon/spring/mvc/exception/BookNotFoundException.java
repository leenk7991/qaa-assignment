package com.learning.atypon.spring.mvc.exception;

public class BookNotFoundException extends RuntimeException {

    private String exceptionMessage;

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause, String exceptionMessage) {
        super(message, cause);
        this.exceptionMessage = exceptionMessage;
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
