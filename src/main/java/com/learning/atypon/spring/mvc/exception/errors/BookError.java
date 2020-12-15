package com.learning.atypon.spring.mvc.exception.errors;

public class BookError {
    private String message;
    private int code;

    public BookError() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BookError(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
