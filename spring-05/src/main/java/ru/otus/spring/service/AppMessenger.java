package ru.otus.spring.service;

import org.springframework.lang.Nullable;

public interface AppMessenger {
    String getMessage(String code);
    String getMessage(String code, @Nullable Object[] args);
}
