package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public interface IOQuestionService {
    void printQuestion(Question question);
    void printResult(String result);
    String readAnswer();
    String readStudentName();
}
