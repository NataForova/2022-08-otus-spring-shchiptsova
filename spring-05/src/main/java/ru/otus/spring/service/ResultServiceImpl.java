package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ApplicationConfig;

@Service
public class ResultServiceImpl implements ResultService {
    private final Integer minScore;
    private final IOQuestionService ioQuestionService;
    private final TestingAppMessenger testingAppMessenger;

    public ResultServiceImpl(IOQuestionService ioQuestionService, @Value("${questions.min.score}") Integer minScore,
                             TestingAppMessenger testingAppMessenger) {
        this.ioQuestionService = ioQuestionService;
        this.minScore = minScore;
        this.testingAppMessenger = testingAppMessenger;
    }

    @Override
    public boolean checkPassed(Integer score) {
        if (score >= this.minScore) {
            ioQuestionService.printResult(testingAppMessenger.getMessage("passed"));
            return true;
        }
        ioQuestionService.printResult(testingAppMessenger.getMessage("not.passed"));
        return false;
    }
}