package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.test.dao.BookDao;
import ru.otus.spring.test.domain.Author;
import ru.otus.spring.test.domain.Book;
import ru.otus.spring.test.domain.Comment;
import ru.otus.spring.test.domain.Genre;
import ru.otus.spring.test.exception.AuthorNotFoundException;
import ru.otus.spring.test.exception.GenreNotFoundException;
import ru.otus.spring.test.service.BookService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Import(TestApplicationConfig.class)
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

    private static final long COMMENT_ONE_EXISTING_ID = 1L;
    private static final long COMMENT_TWO_EXISTING_ID = 2L;

    private static final String COMMENT_ONE_EXISTING_COMMENT = "Good Book";
    private static final String COMMENT_TWO_EXISTING_COMMENT = "I like it!";
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
        List<Comment> comments = new ArrayList<>();
        Comment commentOne = new Comment(COMMENT_ONE_EXISTING_ID,
                EXISTING_BOOK_ID,
                COMMENT_ONE_EXISTING_COMMENT);
        comments.add(commentOne);
        Comment commentTwo = new Comment(COMMENT_TWO_EXISTING_ID,
                EXISTING_BOOK_ID,
                COMMENT_TWO_EXISTING_COMMENT);
        comments.add(commentTwo);
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME,
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME),
                comments);
        Book actualBook = bookService.getBookById(EXISTING_BOOK_ID);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getBookByIdWhenIdNotExistTest() {
        Book book = bookService.getBookById(NOT_EXISTING_ID);
        assertNull(book);
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
