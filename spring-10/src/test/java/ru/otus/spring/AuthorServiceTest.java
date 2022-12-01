package ru.otus.spring;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.test.dao.AuthorDao;
import ru.otus.spring.test.domain.Author;
import ru.otus.spring.test.service.AuthorService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestApplicationConfig.class)
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
