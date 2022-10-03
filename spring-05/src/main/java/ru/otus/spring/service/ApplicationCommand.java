package ru.otus.spring.service;

import org.springframework.shell.standard.ShellOption;

public interface ApplicationCommand {
    String login(String userName);
    String start();
}
