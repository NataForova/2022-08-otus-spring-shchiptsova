package ru.otus.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.IOQuestionServiceImpl;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    @Bean
    IOQuestionServiceImpl getIOQuestionServiceImpl() {
        return new IOQuestionServiceImpl(System.in, System.out);
    }
}
