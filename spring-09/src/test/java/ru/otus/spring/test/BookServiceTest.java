package ru.otus.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.service.BookService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Import(ApplicationTestConfig.class)
@TestConfiguration(value = "application-test.yml")
public class BookServiceTest {

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

    private static final long NOT_EXISTING_ID = 99L;
    @Autowired
    BookDao bookDao;

    @Autowired
    BookService bookService;

    @Test
    void insertBooksTestWhenBookNameIsNull() {
        assertThatThrownBy(() -> bookService.insertBook(null, EXISTING_AUTHOR_ID, EXISTING_BOOK_ID))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("Book name cannot be null");
    }

    @Test
    void insertBooksTestWhenBookNameIBlank() {
        assertThatThrownBy(() -> bookService.insertBook("", EXISTING_AUTHOR_ID, EXISTING_BOOK_ID))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("Book name cannot be empty");
    }

    @Test
    void insertBooksTestWhenAuthorIdNotExist() {
        assertThatThrownBy(() -> bookService.insertBook(NEW_BOOK_NAME, NOT_EXISTING_ID, EXISTING_BOOK_ID))
                .isInstanceOf(AuthorNotFoundException.class);
    }

    @Test
    void insertBooksTestWhenGenreIdNotExist() {
        assertThatThrownBy(() -> bookService.insertBook(NEW_BOOK_NAME, EXISTING_AUTHOR_ID, NOT_EXISTING_ID))
                .isInstanceOf(GenreNotFoundException.class);
    }

    @Test
    void getBookByIdTest() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME,
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        Book actualBook = bookService.getBookById(EXISTING_BOOK_ID);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getBookByIdWhenIdNotExistTest() {
        assertThatThrownBy(() -> bookService.getBookById(NOT_EXISTING_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void getBookByAuthorIdTest() {
        List<Book> bookList = bookService.getBookByAuthorId(EXISTING_AUTHOR_ID);
        Book actualBook = bookList.get(0);
        assertEquals(1, actualBook.getId());
        assertEquals("Sherlock Holmes", actualBook.getName());
        assertEquals("Arthur Conan Doyle", actualBook.getAuthor().getName());
        assertEquals("Detective", actualBook.getGenre().getName());

        actualBook = bookList.get(1);
        assertEquals(2, actualBook.getId());
        assertEquals("Lost World", actualBook.getName());
        assertEquals("Arthur Conan Doyle", actualBook.getAuthor().getName());
        assertEquals("Science fiction", actualBook.getGenre().getName());
    }

    @Test
    void getBookByAuthorIdWhenAuthorIdNotExist() {
        assertThatThrownBy(() -> bookService.getBookByAuthorId(NOT_EXISTING_ID))
                .isInstanceOf(AuthorNotFoundException.class);
    }

    @Test
    void getBookByGenreIdTest() {
        List<Book> bookList = bookService.getBookByGenreId(EXISTING_GENRE_ID);
        Book actualBook = bookList.get(0);
        assertEquals(1, actualBook.getId());
        assertEquals("Sherlock Holmes", actualBook.getName());
        assertEquals("Arthur Conan Doyle", actualBook.getAuthor().getName());
        assertEquals("Detective", actualBook.getGenre().getName());
    }

    @Test
    void getBookByGenreIdWhenGenreIdNotExist() {
        assertThatThrownBy(() -> bookService.getBookByGenreId(NOT_EXISTING_ID))
                .isInstanceOf(GenreNotFoundException.class);
    }

    @Test
    void updateBookNameTestWhenBookNameIsNull() {
        assertThatThrownBy(() -> bookService.updateBook(EXISTING_BOOK_ID, null, EXISTING_AUTHOR_ID, EXISTING_GENRE_ID))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateBookNameTestWhenBookNameIsEmpty() {
        assertThatThrownBy(() -> bookService.updateBook(EXISTING_BOOK_ID, "", EXISTING_AUTHOR_ID, EXISTING_GENRE_ID))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateBookNameWhenAuthorIdNotExistTest() {
        assertThatThrownBy(() -> bookService.updateBook(EXISTING_BOOK_ID, NEW_BOOK_NAME, NOT_EXISTING_ID, EXISTING_GENRE_ID))
                .isInstanceOf(AuthorNotFoundException.class);
    }

    @Test
    void updateBookNameWhenGenreIdNotExistTest() {
        assertThatThrownBy(() -> bookService.updateBook(EXISTING_BOOK_ID, NEW_BOOK_NAME, EXISTING_AUTHOR_ID, NOT_EXISTING_ID))
                .isInstanceOf(GenreNotFoundException.class);
    }

    @Test
    void countBooksTest() {
        assertEquals(EXPECTED_BOOK_COUNT, bookService.countBooks());
    }
}
