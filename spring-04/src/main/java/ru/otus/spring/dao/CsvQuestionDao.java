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
import java.util.stream.Collectors;

@Component
public class CsvQuestionDao implements QuestionDao {

    private final static String DEFAULT_LOCALE_NAME = "ru_RU";

    @Value("${questions.resource.file.mask}")
    private String questionResourceMask;

    private final ApplicationConfig applicationConfig;

    @Autowired
    public CsvQuestionDao(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Override
    public List<String> getQuestions() {
        String localeName = applicationConfig.getLocale().toString();

        InputStream in = SpringBootApplicationMain.class.getClassLoader().getResourceAsStream(questionResourceMask.replace("*", localeName));
        if (in == null) {
            in = SpringBootApplicationMain.class.getClassLoader().getResourceAsStream(questionResourceMask.replace("*", DEFAULT_LOCALE_NAME));
        }
        return new BufferedReader(new InputStreamReader(in))
                .lines()
                .collect(Collectors.toList());
    }
}
