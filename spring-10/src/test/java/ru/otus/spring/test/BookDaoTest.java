package ru.otus.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookDaoJdbc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoTest {
    private static final int EXPECTED_BOOK_COUNT = 7;
    private static final long EXISTING_BOOK_ID = 1L;
    private static final String EXISTING_BOOK_NAME = "Sherlock Holmes";

    private static final long EXISTING_GENRE_ID = 4L;
    private static final String EXISTING_GENRE_NAME = "Detective";

    private static final long EXISTING_AUTHOR_ID = 3L;
    private static final String EXISTING_AUTHOR_NAME = "Arthur Conan Doyle";

    private static final long NEW_BOOK_ID = 4L;
    private static final String NEW_BOOK_NAME = "The Valley of Fear";

    private static final long NEW_GENRE_ID = 5L;
    private static final String NEW_GENRE_NAME = "Novel";

    private static final long NEW_AUTHOR_ID = 1L;
    private static final String NEW_AUTHOR_NAME = "Joanne Rowling";


    @Autowired
    BookDao bookDao;

    @Test
    void bookCountTest() {
        assertEquals(EXPECTED_BOOK_COUNT, bookDao.count());
    }

    @Test
    void bookInsertTest() {
        Book expectedBook = new Book(NEW_BOOK_ID, NEW_BOOK_NAME,
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        bookDao.insert(NEW_BOOK_NAME, EXISTING_AUTHOR_ID, EXISTING_GENRE_ID);

        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void bookGetByIdTest() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME,
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void bookGetAllTest() {
        List<Book> actualBookList = bookDao.getAll();

        assertEquals(EXPECTED_BOOK_COUNT, actualBookList.size());

        Book actualBook = actualBookList.get(0);
        assertEquals(1, actualBook.getId());
        assertEquals("Sherlock Holmes", actualBook.getName());
        assertEquals("Arthur Conan Doyle", actualBook.getAuthor().getName());
        assertEquals("Detective", actualBook.getGenre().getName());

        actualBook = actualBookList.get(1);
        assertEquals(2, actualBook.getId());
        assertEquals("Lost World", actualBook.getName());
        assertEquals("Arthur Conan Doyle", actualBook.getAuthor().getName());
        assertEquals("Science fiction", actualBook.getGenre().getName());

        actualBook = actualBookList.get(2);
        assertEquals(3, actualBook.getId());
        assertEquals("Harry Potter", actualBook.getName());
        assertEquals("Joanne Rowling", actualBook.getAuthor().getName());
        assertEquals("Fantasy", actualBook.getGenre().getName());

        actualBook = actualBookList.get(3);
        assertEquals(4, actualBook.getId());
        assertEquals("The Casual Vacancy", actualBook.getName());
        assertEquals("Joanne Rowling", actualBook.getAuthor().getName());
        assertEquals("Comedy", actualBook.getGenre().getName());

        actualBook = actualBookList.get(4);
        assertEquals(5, actualBook.getId());
        assertEquals("Oliver Twist", actualBook.getName());
        assertEquals("Charles Dickens", actualBook.getAuthor().getName());
        assertEquals("Novel", actualBook.getGenre().getName());

        actualBook = actualBookList.get(5);
        assertEquals(6, actualBook.getId());
        assertEquals("The Mystery of Edwin Droods", actualBook.getName());
        assertEquals("Charles Dickens", actualBook.getAuthor().getName());
        assertEquals("Novel", actualBook.getGenre().getName());

        actualBook = actualBookList.get(6);
        assertEquals(7, actualBook.getId());
        assertEquals("David Copperfield", actualBook.getName());
        assertEquals("Charles Dickens", actualBook.getAuthor().getName());
        assertEquals("Novel", actualBook.getGenre().getName());

    }

    @Test
    void bookUpdateBookNameTest() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, NEW_BOOK_NAME,
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        bookDao.update(expectedBook.getId(), expectedBook.getName(),
                expectedBook.getAuthor().getId(), expectedBook.getGenre().getId());

        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void bookUpdateBookGenreTest() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, NEW_BOOK_NAME,
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(NEW_GENRE_ID, NEW_GENRE_NAME));
        bookDao.update(expectedBook.getId(), expectedBook.getName(),
                expectedBook.getAuthor().getId(), expectedBook.getGenre().getId());

        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void bookUpdateBookAuthorTest() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, NEW_BOOK_NAME,
                new Author(NEW_AUTHOR_ID, NEW_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        bookDao.update(expectedBook.getId(), expectedBook.getName(),
                expectedBook.getAuthor().getId(), expectedBook.getGenre().getId());

        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void bookDeleteByIdTest() {
        assertThatCode(() -> bookDao.getById(EXISTING_BOOK_ID))
                .doesNotThrowAnyException();

        bookDao.deleteById(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> bookDao.getById(EXISTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}
