package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.service.AuthorService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = TestApplicationConfig.class)
public class AuthorServiceTest {
    private final static String NEW_AUTHOR_NAME = "Pushkin";
    @Autowired
    AuthorDao authorDao;

    @Autowired
    AuthorService authorService;


    @Test
    void authorInsertTest() {
        authorService.insert(NEW_AUTHOR_NAME);
        assertThat(authorDao.count()).isGreaterThan(3);
    }
}
