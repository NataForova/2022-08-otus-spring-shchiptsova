package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.IncorrectAnswerException;

public interface AnswerProcessorService {
    boolean checkAnswer(Question question, String studentAnswer) throws IncorrectAnswerException;
    int getScore();
    void resetScore();

}
