package ru.otus.spring.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionConverter {

    public static List<Question> convertCvsToQuestionList(List<String> cvsStringList) throws IOException {
        List<Question> questionList = new ArrayList<>();
        for (String cvsString : cvsStringList) {
            questionList.add(convertCvsToQuestion(cvsString));
        }
        return questionList;
    }

    public static Question convertCvsToQuestion(String cvsString) throws IOException {
        List<String> list = Arrays.asList(cvsString.split(","));
        if (list.size() < 2) {
            throw new IOException("Wrong question file format");
        } else {
            return new Question(list.get(0), list.get(1), list.get(2), list.get(3));
        }
    }
}
