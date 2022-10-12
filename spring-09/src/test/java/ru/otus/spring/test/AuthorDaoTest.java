package ru.otus.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.AuthorDaoJdbc;
import ru.otus.spring.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoTest {

    private static final int EXPECTED_AUTHOR_COUNT = 3;
    private static final long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_NAME = "Joanne Rowling";
    private static final long NEW_AUTHOR_ID = 4L;
    private static final String NEW_AUTHOR_NAME = "Robert Louis Stevenson";


    @Autowired
    AuthorDao authorDao;

    @Test
    void authorCountTest() {
        assertEquals(EXPECTED_AUTHOR_COUNT, authorDao.count());
    }

    @Test
    void authorInsertTest() {
        Author expectedAuthor = new Author(NEW_AUTHOR_ID, NEW_AUTHOR_NAME);
        authorDao.insert(NEW_AUTHOR_NAME);

        Author actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(actualAuthor);
    }

    @Test
    void authorGetByIdTest() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(actualAuthor);
    }

    @Test
    void authorGetAllTest() {
        List<Author> actualAuthorList = authorDao.getAll();

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
        authorDao.update(expectedAuthor);

        Author actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(actualAuthor);
    }

    @Test
    void authorDeleteByIdTest() {
        assertThatCode(() -> authorDao.getById(EXISTING_AUTHOR_ID))
                .doesNotThrowAnyException();

        authorDao.deleteById(EXISTING_AUTHOR_ID);

        assertThatThrownBy(() -> authorDao.getById(EXISTING_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}