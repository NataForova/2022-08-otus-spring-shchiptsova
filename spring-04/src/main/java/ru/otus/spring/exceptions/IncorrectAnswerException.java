package ru.otus.spring.exceptions;


public class IncorrectAnswerException extends RuntimeException {
    public IncorrectAnswerException(String message) {
        super(message);
    }
}
