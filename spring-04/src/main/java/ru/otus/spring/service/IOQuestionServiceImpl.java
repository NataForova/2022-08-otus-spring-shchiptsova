package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOQuestionServiceImpl implements IOQuestionService{

    private final Scanner scanner;
    private final PrintStream outputStream;

    public IOQuestionServiceImpl(InputStream inputStream, PrintStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.outputStream = outputStream;
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
