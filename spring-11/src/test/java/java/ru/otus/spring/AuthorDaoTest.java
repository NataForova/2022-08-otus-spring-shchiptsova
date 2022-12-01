package java.ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.AuthorRepository;
import ru.otus.spring.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(AuthorRepository.class)
public class AuthorDaoTest {

    private static final int EXPECTED_AUTHOR_COUNT = 3;
    private static final long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_NAME = "Joanne Rowling";
    private static final String NEW_AUTHOR_NAME = "Robert Louis Stevenson";

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void authorCountTest() {
        assertEquals(EXPECTED_AUTHOR_COUNT, authorRepository.count());
    }

    @Test
    void authorInsertTest() {
        Author expectedAuthor = new Author();
        expectedAuthor.setName(NEW_AUTHOR_NAME);
        authorRepository.save(expectedAuthor);

        Author actualAuthor = em.find(Author.class, expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void authorGetByIdTest() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorRepository.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(actualAuthor);
    }

    @Test
    void authorGetAllTest() {
        List<Author> actualAuthorList = authorRepository.findAll();

        assertEquals(EXPECTED_AUTHOR_COUNT, actualAuthorList.size());

        Author actualAuthor = actualAuthorList.get(0);
        assertEquals(1, actualAuthor.getId());
        assertEquals("Joanne Rowling", actualAuthor.getName());

        actualAuthor = actualAuthorList.get(1);
        assertEquals(2, actualAuthor.getId());
        assertEquals("Charles Dickens", actualAuthor.getName());

        actualAuthor = actualAuthorList.get(2);
        assertEquals(3, actualAuthor.getId());
        assertEquals("Arthur Conan Doyle", actualAuthor.getName());

    }

    @Test
    void authorUpdateTest() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, NEW_AUTHOR_NAME);
        authorRepository.save(expectedAuthor);

        Author actualAuthor = authorRepository.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(actualAuthor);
    }

    @Test
    void authorDeleteByIdTest() {
        assertThatCode(() -> authorRepository.getById(EXISTING_AUTHOR_ID))
                .doesNotThrowAnyException();

        authorRepository.deleteById(EXISTING_AUTHOR_ID);

        assertNull(authorRepository.getById(EXISTING_AUTHOR_ID));
    }
}
