package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvQuestionDao implements QuestionDao {
    @Value("${questions.resource.file}")
    private String questionResource;

    public CsvQuestionDao() {
    }

    @Override
    public List<String> getQuestions() {
        InputStream in = Main.class.getClassLoader().getResourceAsStream(this.questionResource);
        return new BufferedReader(new InputStreamReader(in))
                .lines()
                .collect(Collectors.toList());
    }
}
