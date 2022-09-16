package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {
    private final Integer minScore;
    private final IOQuestionService ioQuestionService;

    public ResultServiceImpl(IOQuestionService ioQuestionService, @Value("${questions.min.score}") Integer minScore) {
        this.ioQuestionService = ioQuestionService;
        this.minScore = minScore;
    }

    @Override
    public boolean checkPassed(Integer score) {
        if (score >= this.minScore) {
            ioQuestionService.printResult("You passed!!!\n");
            return true;
        }
        ioQuestionService.printResult("You didn't passed :(\n");
        return false;
    }
}