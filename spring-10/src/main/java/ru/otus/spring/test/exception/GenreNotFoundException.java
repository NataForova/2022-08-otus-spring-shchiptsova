package ru.otus.spring.test.exception;

public class GenreNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Library don't have genre with such id. \n" +
            "First you need to add a genre";
    public GenreNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public GenreNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
