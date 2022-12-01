package java.ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.AuthorRepository;
import ru.otus.spring.service.AuthorService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestApplicationConfig.class)
public class AuthorServiceTest {
    private final static String NEW_AUTHOR_NAME = "Pushkin";
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorService authorService;


    @Test
    void authorInsertTest() {
        authorService.insert(NEW_AUTHOR_NAME);
        assertThat(authorRepository.count()).isGreaterThan(3);
    }
}
