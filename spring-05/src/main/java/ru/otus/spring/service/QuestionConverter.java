package ru.otus.spring.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.CsvFormatConvertException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class QuestionConverter implements CvsConverter<Question> {

    private final TestingAppMessenger testingAppMessenger;

    public QuestionConverter(TestingAppMessenger testingAppMessenger) {
        this.testingAppMessenger = testingAppMessenger;
    }

    @Override
    public List<Question> convertCvsToQuestionList(List<String> cvsStringList) throws CsvFormatConvertException {
        List<Question> questionList = new ArrayList<>();
        for (String cvsString : cvsStringList) {
            questionList.add(convertCvsToQuestion(cvsString));
        }
        return questionList;
    }

    @Override
    public Question convertCvsToQuestion(String cvsString) throws CsvFormatConvertException {
        List<String> list = Arrays.asList(cvsString.split(","));
        if (list.size() < 4) {
            throw new CsvFormatConvertException(testingAppMessenger.getMessage("wrong.question.file.format"));
        } else {
            return new Question(list.get(0), list.get(1), list.get(2), list.get(3));
        }
    }
}
