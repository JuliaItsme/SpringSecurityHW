package ru.goryacheva.springsecurityhw.exception;

public class AuthException extends RuntimeException {
    private int status;

    public AuthException() {
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(int status, String message) {
        super(message);
        this.status = status;
    }
}
