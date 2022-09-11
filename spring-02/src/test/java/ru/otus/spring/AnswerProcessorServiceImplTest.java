package ru.otus.spring;

import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.IncorrectAnswerException;
import ru.otus.spring.service.AnswerProcessorService;
import ru.otus.spring.service.AnswerProcessorServiceImpl;
import ru.otus.spring.service.IOQuestionService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class AnswerProcessorServiceImplTest {

    @Test
    void checkAnswerTestWhenWrongAnswer() {
        IOQuestionService ioQuestionService = mock(IOQuestionService.class);
        AnswerProcessorService answerProcessorService = new AnswerProcessorServiceImpl(ioQuestionService);
        Question question = new Question("Question?", "A", "B", "A");
        assertThat(answerProcessorService.checkAnswer(question, "B")).isFalse();
    }

    @Test
    void checkAnswerTestWhenRightAnswer() {
        IOQuestionService ioQuestionService = mock(IOQuestionService.class);
        AnswerProcessorService answerProcessorService = new AnswerProcessorServiceImpl(ioQuestionService);
        Question question = new Question("Question?", "A", "B", "A");
        assertThat(answerProcessorService.checkAnswer(question, "A")).isTrue();
    }

    @Test
    void checkAnswerTestWhenRightAnswerIsNull() {
        IOQuestionService ioQuestionService = mock(IOQuestionService.class);
        AnswerProcessorService answerProcessorService = new AnswerProcessorServiceImpl(ioQuestionService);
        Question question = new Question("Question?", "A", "B", null);
        assertThrows(IncorrectAnswerException.class,
                ()-> answerProcessorService.checkAnswer(question, "А"));
    }

    @Test
    void checkAnswerTestWhenStudentAnswerIsNull() {
        IOQuestionService ioQuestionService = mock(IOQuestionService.class);
        AnswerProcessorService answerProcessorService = new AnswerProcessorServiceImpl(ioQuestionService);
        Question question = new Question("Question?", "A", "B", "A");
        assertThrows(IncorrectAnswerException.class,
                ()-> answerProcessorService.checkAnswer(question, null));
    }
}
