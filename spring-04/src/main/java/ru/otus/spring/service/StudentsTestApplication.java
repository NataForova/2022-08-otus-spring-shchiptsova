package ru.otus.spring.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.CsvFormatConvertException;
import ru.otus.spring.exceptions.IncorrectAnswerException;

import java.util.List;

@Component
public class StudentsTestApplication implements CommandLineRunner {
    private final IOQuestionService ioQuestionService;
    private final QuestionDao questionDao;
    private final AnswerProcessorService answerProcessorService;
    private final ResultService resultService;
    private final TestingStopService testingStopService;
    private final QuestionConverter converter;
    private final TestingAppMessenger testingAppMessenger;

    public StudentsTestApplication(IOQuestionService ioQuestionService, QuestionDao questionDao,
                                   AnswerProcessorService answerProcessorService, ResultService resultService,
                                   TestingStopService testingStopService, QuestionConverter questionConverter,
                                   TestingAppMessenger testingAppMessenger) {
        this.ioQuestionService = ioQuestionService;
        this.questionDao = questionDao;
        this.answerProcessorService = answerProcessorService;
        this.resultService = resultService;
        this.testingStopService = testingStopService;
        this.converter = questionConverter;
        this.testingAppMessenger = testingAppMessenger;
    }

    @Override
    public void run(String... args)  {
        while (testingStopService.isTestingRunning()) {
            try {
                runStudentTesting();
            } catch (CsvFormatConvertException e) {
                ioQuestionService.printResult(testingAppMessenger.getMessage("question.file.problem"));
            } catch (IncorrectAnswerException e) {
                ioQuestionService.printResult(e.getMessage());
            }
        }
    }

    private void runStudentTesting() throws IncorrectAnswerException, CsvFormatConvertException {
        ioQuestionService.printResult(testingAppMessenger.getMessage("greeting"));
        String studentName = ioQuestionService.readStudentName();

        List<Question> questions = converter.convertCvsToQuestionList(questionDao.getQuestions());

        for (Question question : questions) {
            ioQuestionService.printQuestion(question);
            String answer = ioQuestionService.readAnswer();
            answerProcessorService.checkAnswer(question, answer);
        }

        Integer totalStudentScore = answerProcessorService.getScore();
        String [] args = new String[2];
        args[0] = studentName;
        args[1] = totalStudentScore.toString();
        ioQuestionService.printResult(testingAppMessenger.getMessage("total.result", args));
        testingStopService.stopTesting(resultService.checkPassed(totalStudentScore));
        answerProcessorService.resetScore();
    }
}
