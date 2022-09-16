package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestingStopServiceImpl implements TestingStopService {
    private final IOQuestionService ioQuestionService;
    private boolean isTestingRunning;

    @Autowired
    public TestingStopServiceImpl(IOQuestionService ioQuestionService) {
        this.ioQuestionService = ioQuestionService;
        this.isTestingRunning = true;
    }

    @Override
    public boolean isTestingRunning() {
        return this.isTestingRunning;
    }

    @Override
    public void stopTesting(boolean isTestPassed) {
        if (!isTestPassed) {
            ioQuestionService.printResult("Do you want to try one more time? y/n \n");
            String answer = ioQuestionService.readAnswer();
            this.isTestingRunning = answer.equalsIgnoreCase("y");
        } else {
            this.isTestingRunning = false;
        }
    }
}
