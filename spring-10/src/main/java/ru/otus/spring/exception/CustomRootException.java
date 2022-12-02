package ru.otus.spring.exception;

public class CustomRootException extends RuntimeException {
    CustomRootException(String message) {
        super(message);
    }

    CustomRootException(String message, Throwable cause) {
        super(message, cause);
    }
}
