package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionConverter;
import ru.otus.spring.exceptions.CsvFormatConvertException;
import ru.otus.spring.exceptions.IncorrectAnswerException;

import java.io.IOException;
import java.util.List;

@Component
public class ApplicationRunner {
    private final IOQuestionService ioQuestionService;
    private final QuestionDao questionDao;
    private final AnswerProcessorService answerProcessorService;
    private final ResultService resultService;
    private final TestingStopService testingStopService;
    @Autowired
    public ApplicationRunner(IOQuestionService ioQuestionService, QuestionDao questionDao,
                             AnswerProcessorService answerProcessorService, ResultService resultService,
                             TestingStopService testingStopService) {
        this.ioQuestionService = ioQuestionService;
        this.questionDao = questionDao;
        this.answerProcessorService = answerProcessorService;
        this.resultService = resultService;
        this.testingStopService = testingStopService;
    }

    public void run()  {
        while (testingStopService.isTestingRunning()) {
            try {
                runStudentTesting();
            } catch (CsvFormatConvertException e) {
                ioQuestionService.printResult("We have problem with the test file. Please tell this information to a teacher");
            } catch (IncorrectAnswerException e) {
                ioQuestionService.printResult(e.getMessage());
            }
        }
    }

    private void runStudentTesting() throws IncorrectAnswerException, CsvFormatConvertException {
        ioQuestionService.printResult("Welcome to the students test! \nWhat is your name?\n");
        String studentName = ioQuestionService.readStudentName();

        List<Question> questions = QuestionConverter.convertCvsToQuestionList(questionDao.getQuestions());

        for (Question question : questions) {
            ioQuestionService.printQuestion(question);
            String answer = ioQuestionService.readAnswer();
            answerProcessorService.checkAnswer(question, answer);
        }

        Integer totalStudentScore = answerProcessorService.getScore();
        ioQuestionService.printResult(studentName +  " your score = " + totalStudentScore + "\n");
        testingStopService.stopTesting(resultService.checkPassed(totalStudentScore));
        answerProcessorService.resetScore();
    }
}
