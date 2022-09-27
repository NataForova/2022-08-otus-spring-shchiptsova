package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ApplicationConfig;

@Service
public class TestingStopServiceImpl implements TestingStopService {
    private final IOQuestionService ioQuestionService;
    private final TestingAppMessenger testingAppMessenger;
    private boolean isTestingRunning;

    @Autowired
    public TestingStopServiceImpl(IOQuestionService ioQuestionService, TestingAppMessenger testingAppMessenger) {
        this.ioQuestionService = ioQuestionService;
        this.testingAppMessenger = testingAppMessenger;
        this.isTestingRunning = true;
    }

    @Override
    public boolean isTestingRunning() {
        return this.isTestingRunning;
    }

    @Override
    public void stopTesting(boolean isTestPassed) {
        if (!isTestPassed) {
            ioQuestionService.printResult(testingAppMessenger.getMessage("next.try"));
            String answer = ioQuestionService.readAnswer();
            this.isTestingRunning = answer.equalsIgnoreCase("y");
        } else {
            this.isTestingRunning = false;
        }
    }
}
