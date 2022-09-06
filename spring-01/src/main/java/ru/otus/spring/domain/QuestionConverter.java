package ru.otus.spring.domain;

import java.util.Arrays;
import java.util.List;

public class QuestionConverter {
    public static Question convertToQuestion(String cvsString) {
        List<String> list = Arrays.asList(cvsString.split(","));
        if (list.size() == 1) {
            return new Question(list.get(0), "-", "-");
        } else {
            return new Question(list.get(0), list.get(1), list.get(2));
        }
    }
}
