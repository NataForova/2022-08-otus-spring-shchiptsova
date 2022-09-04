package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import ru.otus.spring.Main;
import ru.otus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDao {
    @Value("questionResource")
    private String questionResource;

    public QuestionDao() {
    }

    public List<Question> getQuestions() {
        InputStream in = Main.class.getClassLoader().getResourceAsStream(this.questionResource);
        return new BufferedReader(new InputStreamReader(in))
                .lines()
                .map(this::convertToQuestion)
                .collect(Collectors.toList());
    }

    private Question convertToQuestion(String cvsString) {
        List<String> list = Arrays.asList(cvsString.split(","));
        if (list.size() == 1) {
            return new Question(list.get(0), "-", "-");
        } else {
            return new Question(list.get(0), list.get(1), list.get(2));
        }
    }

    public void setQuestionResource(String questionResource) {
        this.questionResource = questionResource;
    }
}
