package ru.otus.spring.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.IOQuestionService;
import ru.otus.spring.service.IOQuestionServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOQuestionServiceImplTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ByteArrayInputStream inContent;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void printQuestionWhenAllRight() {
        IOQuestionService ioQuestionService = new IOQuestionServiceImpl(System.in, System.out);
        Question question = new Question("We will, we will...", "rock you", "be cats", "A");

        ioQuestionService.printQuestion(question);
        assertEquals(question.toString(), outContent.toString());
    }

    @Test
    void printQuestionWhenAnswerIsNull() {
        IOQuestionService ioQuestionService = new IOQuestionServiceImpl(System.in, System.out);
        Question question = new Question("To be or not to be", "to be", "not to be", null);

        ioQuestionService.printQuestion(question);
        assertEquals(question.toString(), outContent.toString());
    }

    @Test
    void readAnswerTest() {
        String testAnswer = "42";
        inContent = new ByteArrayInputStream(testAnswer.getBytes());
        System.setIn(inContent);

        IOQuestionService ioQuestionService = new IOQuestionServiceImpl(inContent, System.out);

        String answer = ioQuestionService.readAnswer();
        assertEquals(testAnswer, answer);
    }

    @Test
    void printResultTestWhenAllRight() {
        IOQuestionService ioQuestionService = new IOQuestionServiceImpl(System.in, System.out);
        ioQuestionService.printResult("Show must go on!");

        assertEquals("Show must go on!", outContent.toString());
    }
}
