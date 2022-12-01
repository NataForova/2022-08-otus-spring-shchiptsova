package ru.otus.spring.test.exception;

public class CustomRootException extends RuntimeException {
    CustomRootException(String message) {
        super(message);
    }

    CustomRootException(String message, Throwable cause) {
        super(message, cause);
    }
}
