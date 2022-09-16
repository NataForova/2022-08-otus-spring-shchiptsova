package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.IOQuestionService;
import ru.otus.spring.service.IOQuestionServiceImpl;

import java.util.Locale;

@ComponentScan
@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationConfig {
    private String message;
    private Locale locale;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Bean
    IOQuestionService getIOQuestionService() {
        return new IOQuestionServiceImpl(System.in, System.out);
    }
}
