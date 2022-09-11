package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Question;

@Component
public class AnswerProcessorImpl implements AnswerProcessor {

    private Integer score ;
    private final IOQuestionService ioQuestionService;

    @Autowired
    public AnswerProcessorImpl(IOQuestionService ioQuestionService) {
        this.score = 0;
        this.ioQuestionService = ioQuestionService;
    }

    @Override
    public boolean checkAnswer(Question question, String answer) {
        if (getBareOption(question.getRightAnswer()).equals(getBareOption(answer))) {
            score++;
            ioQuestionService.printResult("Right! \n");
            return true;
        } else {
            ioQuestionService.printResult("Wrong :( \n");
            return false;
        }
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void resetScore() {
        this.score = 0;
    }

    private String getBareOption(String option) {
        return option.trim().toLowerCase();
    }
}
