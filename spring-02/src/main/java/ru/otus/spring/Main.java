package ru.otus.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import ru.otus.spring.service.ApplicationRunner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ApplicationRunner runner = context.getBean(ApplicationRunner.class);
        runner.run();
    }
}
