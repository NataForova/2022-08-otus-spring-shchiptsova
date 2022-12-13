package ru.otus.spring.exception;

public class CommentNotFoundException extends CustomRootException {

    private static final String DEFAULT_MESSAGE = "Library don't have comment with such id.";
    public CommentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public CommentNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
