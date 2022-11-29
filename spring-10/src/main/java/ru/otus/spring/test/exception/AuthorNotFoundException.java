package ru.otus.spring.test.exception;

public class AuthorNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Library don't have author with such id. \n" +
            "First you need to add an author";
    public AuthorNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public AuthorNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
