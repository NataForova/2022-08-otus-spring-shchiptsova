package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;

@ShellComponent
public class ApplicationCommand {

    private String userName;
    private final StudentsTestApplication studentsTestApplication;
    private final TestingAppMessenger testingAppMessenger;

    public ApplicationCommand(StudentsTestApplication studentsTestApplication, TestingAppMessenger testingAppMessenger) {
        this.studentsTestApplication = studentsTestApplication;
        this.testingAppMessenger = testingAppMessenger;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "John Doe")String userName) {
        this.userName = userName;
        return testingAppMessenger.getMessage("login.name", userName.split(" "));
    }

    @ShellMethod(value = "Application start command", key = {"s", "start"})
    public String start() throws Exception {
        studentsTestApplication.run(this.userName);
        return testingAppMessenger.getMessage("good.luck");
    }
}
