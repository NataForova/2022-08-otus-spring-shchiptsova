package ru.otus.spring.service;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellApplicationCommand implements ApplicationCommand {

    private String userName;
    private final StudentsTestApplication studentsTestApplication;
    private final TestingAppMessenger testingAppMessenger;

    public ShellApplicationCommand(StudentsTestApplication studentsTestApplication, TestingAppMessenger testingAppMessenger) {
        this.studentsTestApplication = studentsTestApplication;
        this.testingAppMessenger = testingAppMessenger;
    }

    @Override
    @ShellMethod(value = "Login command", key = {"l", "login"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String login(@ShellOption(defaultValue = "John Doe")String userName) {
        this.userName = userName;
        return testingAppMessenger.getMessage("login.name",  new String[] {userName});
    }

    @Override
    @ShellMethod(value = "Application start command", key = {"s", "start"})
    public String start() {
        studentsTestApplication.run(this.userName);
        return testingAppMessenger.getMessage("good.luck");
    }

    private Availability isPublishEventCommandAvailable() {
        return userName == null? Availability.unavailable(testingAppMessenger.getMessage("no.nameK")): Availability.available();
    }
}
