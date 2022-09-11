package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionConverter;
import java.io.IOException;
import java.util.List;

@Component
public class ApplicationRunner {
    private final IOQuestionService ioQuestionService;
    private final QuestionDao questionDao;
    private final AnswerProcessor answerProcessor;
    private final ResultService resultService;
    private final TestingStopService testingStopService;
    @Autowired
    public ApplicationRunner(IOQuestionService ioQuestionService, QuestionDao questionDao,
                             AnswerProcessor answerProcessor, ResultService resultService,
                             TestingStopService testingStopService) {
        this.ioQuestionService = ioQuestionService;
        this.questionDao = questionDao;
        this.answerProcessor = answerProcessor;
        this.resultService = resultService;
        this.testingStopService = testingStopService;
    }

    public void run()  {
        while (testingStopService.isTestingRunning()) {
            try {
                runStudentTesting();
            } catch (IOException e) {
                ioQuestionService.printResult("We have problem with the test file. Please tell this information to a teacher");
            }
        }
    }

    private void runStudentTesting() throws IOException {
        ioQuestionService.printResult("Welcome to the students test! \nWhat is your name?\n");
        String studentName = ioQuestionService.readStudentName();

        List<Question> questions = QuestionConverter.convertCvsToQuestionList(questionDao.getQuestions());

        for (Question question : questions) {
            ioQuestionService.printQuestion(question);
            String answer = ioQuestionService.readAnswer();
            answerProcessor.checkAnswer(question, answer);
        }

        Integer totalStudentScore = answerProcessor.getScore();
        ioQuestionService.printResult(studentName +  " your score = " + totalStudentScore + "\n");
        testingStopService.stopTesting(resultService.checkPassed(totalStudentScore));
        answerProcessor.resetScore();
    }
}
