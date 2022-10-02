package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.service.IOQuestionService;
import ru.otus.spring.service.ResultServiceImpl;
import ru.otus.spring.service.TestingAppMessenger;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class ResultServiceImplTest {
    @Mock
    IOQuestionService ioQuestionService;

    @Mock
    TestingAppMessenger testingAppMessenger;

    @Test
    void checkPassedTestWhenScoreLessThanMin() {
        ResultServiceImpl resultService = new ResultServiceImpl(ioQuestionService, 3, testingAppMessenger);
        assertThat(resultService.checkPassed(2)).isFalse();
    }

    @Test
    void checkPassedTestWhenScoreGreaterThanMin() {
        ResultServiceImpl resultService = new ResultServiceImpl(ioQuestionService, 3, testingAppMessenger);
        assertThat(resultService.checkPassed(4)).isTrue();
    }
}
