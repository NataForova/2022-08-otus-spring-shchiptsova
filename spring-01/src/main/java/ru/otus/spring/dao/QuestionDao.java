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

public class QuestionDao implements QuestionDaoInterface {
    @Value("questionResource")
    private String questionResource;

    public QuestionDao() {
    }

    public void setQuestionResource(String questionResource) {
        this.questionResource = questionResource;
    }

    @Override
    public List<String> getQuestions() {
        InputStream in = Main.class.getClassLoader().getResourceAsStream(this.questionResource);
        return new BufferedReader(new InputStreamReader(in))
                .lines()
                .collect(Collectors.toList());
    }
}
