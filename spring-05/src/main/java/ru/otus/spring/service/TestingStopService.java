package ru.otus.spring.service;

public interface TestingStopService {
    boolean isTestingRunning();
    void stopTesting(boolean isTestPassed);
}
