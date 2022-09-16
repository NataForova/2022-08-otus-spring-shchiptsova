package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.service.IOQuestionService;
import ru.otus.spring.service.TestingStopServiceImpl;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class TestingStopServiceImplTest {
    @Mock
    IOQuestionService ioQuestionService;

    @Test
    void stopTestingWhenTestPassesIsTrue() {
        TestingStopServiceImpl testingStopService = new TestingStopServiceImpl(ioQuestionService);
        testingStopService.stopTesting(true);
        assertThat(testingStopService.isTestingRunning()).isFalse();
    }

    @Test
    void stopTestingWhenTestNotPassesIsFalseAndUserAnswerYes() {
        given(ioQuestionService.readAnswer()).willReturn("y");
        TestingStopServiceImpl testingStopService = new TestingStopServiceImpl(ioQuestionService);
        testingStopService.stopTesting(false);
        assertThat(testingStopService.isTestingRunning()).isTrue();
    }

    @Test
    void stopTestingWhenTestNotPassesIsFalseAndUserAnswerNo() {
        given(ioQuestionService.readAnswer()).willReturn("n");
        TestingStopServiceImpl testingStopService = new TestingStopServiceImpl(ioQuestionService);
        testingStopService.stopTesting(false);
        assertThat(testingStopService.isTestingRunning()).isFalse();
    }
}
