package ru.otus.spring.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Question;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOQuestionServiceImpl implements IOQuestionService{

    private final Scanner scanner;
    private final PrintStream outputStream;

    public IOQuestionServiceImpl(InputStream inputStream, PrintStream outputStream) {
        this.scanner = new Scanner(System.in);
        this.outputStream = System.out;
    }

    @Override
    public void printQuestion(Question question) {
        outputStream.print(question.toString());
    }

    @Override
    public void printResult(String result) {
        outputStream.print(result);
    }

    @Override
    public String readAnswer() {
        return scanner.nextLine();
    }

    @Override
    public String readStudentName() {
        return scanner.nextLine();
    }
}
