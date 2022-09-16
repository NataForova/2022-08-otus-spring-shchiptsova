package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.service.StudentsTestApplication;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class SpringBootApplicationMain implements CommandLineRunner {

    private final StudentsTestApplication studentsTestApplication;

    @Autowired
    public SpringBootApplicationMain(StudentsTestApplication studentsTestApplication) {
        this.studentsTestApplication = studentsTestApplication;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.studentsTestApplication.run();
    }
}
