package ru.otus.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.dao.CsvQuestionDao;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.CsvFormatConvertException;
import ru.otus.spring.service.*;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = QuestionConverter.class)
public class QuestionConverterTest {
    @MockBean
    private TestingAppMessenger testingAppMessenger;

    @Autowired
    private QuestionConverter questionConverter;

    @Test
    public void questionConverterTestWhenAllRigth() {
        Question question = questionConverter.convertCvsToQuestion("To be or not to be,to be,not to be,a");
        assertEquals("To be or not to be", question.getQuestion());
        assertEquals("a", question.getRightAnswer());
        assertEquals("to be", question.getOptionA());
        assertEquals("not to be", question.getOptionB());
    }

    @Test
    public void questionConverterTestWheWrongQuestionFormat() {
        given(testingAppMessenger.getMessage("wrong.question.file.format")).willReturn("Wrong question file format");
        try {
            questionConverter.convertCvsToQuestion("To be or not to be,to be,not to be");
            fail();
        } catch (CsvFormatConvertException e) {
            assertEquals("Wrong question file format", e.getMessage());
        }

    }
}
