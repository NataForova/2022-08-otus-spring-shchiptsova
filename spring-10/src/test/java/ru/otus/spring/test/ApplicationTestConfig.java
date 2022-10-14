package ru.otus.spring.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.TestPropertySource;

@SpringBootApplication(scanBasePackages = {"ru.otus.spring.service", "ru.otus.spring.dao"})
public class ApplicationTestConfig {

}