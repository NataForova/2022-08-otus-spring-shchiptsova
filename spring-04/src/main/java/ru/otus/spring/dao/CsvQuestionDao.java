package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.SpringBootApplicationMain;
import ru.otus.spring.config.ApplicationConfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class CsvQuestionDao implements QuestionDao {
    @Value("${questions.resource.file.en}")
    private String questionResource;

    @Value("${questions.resource.file.ru}")
    private String questionResourceRu;

    private final ApplicationConfig applicationConfig;

    @Autowired
    public CsvQuestionDao(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Override
    public List<String> getQuestions() {
        InputStream in = SpringBootApplicationMain.class.getClassLoader().getResourceAsStream(!applicationConfig.getLocale().equals(Locale.ENGLISH) ?
                this.questionResourceRu : this.questionResource);
        return new BufferedReader(new InputStreamReader(in))
                .lines()
                .collect(Collectors.toList());
    }
}
