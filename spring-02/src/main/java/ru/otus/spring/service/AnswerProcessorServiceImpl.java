package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.IncorrectAnswerException;

@Component
public class AnswerProcessorServiceImpl implements AnswerProcessorService {

    private Integer score;
    private final IOQuestionService ioQuestionService;

    @Autowired
    public AnswerProcessorServiceImpl(IOQuestionService ioQuestionService) {
        this.score = 0;
        this.ioQuestionService = ioQuestionService;
    }

    @Override
    public boolean checkAnswer(Question question, String answer) throws IncorrectAnswerException {
        if (getBareAnswer(question.getRightAnswer()).equals(getBareAnswer(answer))) {
            score++;
            ioQuestionService.printResult("Right! \n");
            return true;
        }
        ioQuestionService.printResult("Wrong :( \n");
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
            throw new IncorrectAnswerException("Answer can't be null");
        }
    }
}
