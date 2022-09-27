package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.IncorrectAnswerException;
import ru.otus.spring.service.AnswerProcessorService;
import ru.otus.spring.service.AnswerProcessorServiceImpl;
import ru.otus.spring.service.IOQuestionService;
import ru.otus.spring.service.TestingAppMessenger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AnswerProcessorServiceImplTest {
    @Mock
    IOQuestionService ioQuestionService;

    @Mock
    TestingAppMessenger testingAppMessenger;

    @Test
    void checkAnswerTestWhenWrongAnswer() {
        AnswerProcessorService answerProcessorService = new AnswerProcessorServiceImpl(ioQuestionService, testingAppMessenger);
        Question question = new Question("We will, we will...", "rock you", "be apples", "A");
        assertThat(answerProcessorService.checkAnswer(question, "B")).isFalse();
    }

    @Test
    void checkAnswerTestWhenRightAnswer() {
        AnswerProcessorService answerProcessorService = new AnswerProcessorServiceImpl(ioQuestionService, testingAppMessenger);
        Question question = new Question("We will, we will...", "rock you", "be apples", "A");
        assertThat(answerProcessorService.checkAnswer(question, "A")).isTrue();
    }

    @Test
    void checkAnswerTestWhenRightAnswerIsNull() {
        AnswerProcessorService answerProcessorService = new AnswerProcessorServiceImpl(ioQuestionService, testingAppMessenger);
        Question question = new Question("To be or not to be", "to be", "not to be", null);
        assertThrows(IncorrectAnswerException.class,
                ()-> answerProcessorService.checkAnswer(question, "А"));
    }

    @Test
    void checkAnswerTestWhenStudentAnswerIsNull() {
        AnswerProcessorService answerProcessorService = new AnswerProcessorServiceImpl(ioQuestionService, testingAppMessenger);
        Question question = new Question("We will, we will...", "rock you", "be apples", "A");
        assertThrows(IncorrectAnswerException.class,
                ()-> answerProcessorService.checkAnswer(question, null));
    }
}
