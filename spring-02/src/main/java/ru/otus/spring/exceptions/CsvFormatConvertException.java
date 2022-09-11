package ru.otus.spring.exceptions;

import java.io.IOException;

public class CsvFormatConvertException extends IOException {
    public CsvFormatConvertException(String message) {
        super(message);
    }
}
