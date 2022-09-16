package ru.otus.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import ru.otus.spring.service.StudentsTestRunner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        StudentsTestRunner runner = context.getBean(StudentsTestRunner.class);
        runner.run();
    }
}
