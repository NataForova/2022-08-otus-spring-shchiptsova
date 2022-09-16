package ru.otus.spring.exceptions;

public class CsvFormatConvertException extends RuntimeException {
    public CsvFormatConvertException(String message) {
        super(message);
    }
}
