package ru.otus.spring.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.IncorrectAnswerException;

@Component
public class AnswerProcessorServiceImpl implements AnswerProcessorService {

    private Integer score;
    private final IOQuestionService ioQuestionService;
    private final TestingAppMessenger testingAppMessenger;

    public AnswerProcessorServiceImpl(IOQuestionService ioQuestionService, TestingAppMessenger testingAppMessenger) {
        this.score = 0;
        this.ioQuestionService = ioQuestionService;
        this.testingAppMessenger = testingAppMessenger;
    }

    @Override
    public boolean checkAnswer(Question question, String answer) throws IncorrectAnswerException {
        if (getBareAnswer(question.getRightAnswer()).equals(getBareAnswer(answer))) {
            score++;
            ioQuestionService.printResult(testingAppMessenger.getMessage("right.answer"));
            return true;
        }
        ioQuestionService.printResult(testingAppMessenger.getMessage("wrong.answer"));
        return false;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void resetScore() {
        this.score = 0;
    }

    private String getBareAnswer(String answer) throws IncorrectAnswerException {
        if (answer != null) {
            return answer.trim().toLowerCase();
        } else {
            throw new IncorrectAnswerException(testingAppMessenger.getMessage("answer.is.null.error"));
        }
    }
}
