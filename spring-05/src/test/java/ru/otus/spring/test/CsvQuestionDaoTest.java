package ru.otus.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.dao.CsvQuestionDao;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Import(ApplicationTestConfig.class)
public class CsvQuestionDaoTest {

    @MockBean
    private ApplicationConfig applicationConfig;

    @Autowired
    private CsvQuestionDao questionDao;

    @Test
    public void getQuestionsTest() {
        given(applicationConfig.getLocale()).willReturn(Locale.ENGLISH);
        List<String> questionsList = questionDao.getQuestions();
        assertEquals(5, questionsList.size());
        assertEquals("What is the capital of Great Britain, Manchester, London,b", questionsList.get(0));
        assertEquals("What is the name of the Queen, Elizabeth, Victoria,a", questionsList.get(1));
        assertEquals("What is the name of the biggest river in London, The Seine, The Thames,b", questionsList.get(2));
        assertEquals("What is the name of the best British rock band, Queen, Hurts,a", questionsList.get(3));
        assertEquals("How many letters are in the English alphabet, 33, 26,b", questionsList.get(4));
    }
}
