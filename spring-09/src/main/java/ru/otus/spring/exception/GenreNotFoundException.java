package ru.otus.spring.exception;

public class GenreNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Library don't have genre with such id. \n" +
            "First you need to add a genre";
    public GenreNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}