package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.ApplicationConfig;

@Component
public class TestingAppMessenger implements AppMessenger {
    private final MessageSource messageSource;
    private final ApplicationConfig applicationConfig;

    @Autowired
    public TestingAppMessenger(MessageSource messageSource, ApplicationConfig applicationConfig) {
        this.messageSource = messageSource;
        this.applicationConfig = applicationConfig;
    }

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, applicationConfig.getLocale());
    }

    @Override
    public String getMessage(String code, @Nullable Object[] args) {
        return messageSource.getMessage(code, args, applicationConfig.getLocale());
    }
}
