package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookDaoJpa;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestApplicationConfig.class)
@Import(BookDaoJpa.class)
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

    @Autowired
    private TestEntityManager em;

    @Test
    void bookCountTest() {
        assertEquals(EXPECTED_BOOK_COUNT, bookDao.count());
    }

    @Test
    void bookInsertTest() {
        Book expectedBook = new Book();
        expectedBook.setName(NEW_BOOK_NAME);
        expectedBook.setAuthor(new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME));
        expectedBook.setGenre(new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        bookDao.save(expectedBook);

        Book actualBook = em.find(Book.class, expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void bookGetByIdTest() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME,
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
               new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME), Collections.emptyList());
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void bookGetAllTest() {
        List<Book> actualBookList = bookDao.getAll();

        assertEquals(EXPECTED_BOOK_COUNT, actualBookList.size());

        Book actualBook = actualBookList.get(0);
        Author authors = actualBook.getAuthor();
        Genre genres = actualBook.getGenre();
        assertEquals(1, actualBook.getId());
        assertEquals("Sherlock Holmes", actualBook.getName());
        assertEquals("Arthur Conan Doyle", authors.getName());
        assertEquals("Detective", genres.getName());

        actualBook = actualBookList.get(1);
        authors = actualBook.getAuthor();
        genres = actualBook.getGenre();
        assertEquals(2, actualBook.getId());
        assertEquals("Lost World", actualBook.getName());
        assertEquals("Arthur Conan Doyle", authors.getName());
        assertEquals("Science fiction", genres.getName());

        actualBook = actualBookList.get(2);
        authors = actualBook.getAuthor();
        genres = actualBook.getGenre();
        assertEquals(3, actualBook.getId());
        assertEquals("Harry Potter", actualBook.getName());
        assertEquals("Joanne Rowling", authors.getName());
        assertEquals("Fantasy", genres.getName());

        actualBook = actualBookList.get(3);
        authors = actualBook.getAuthor();
        genres = actualBook.getGenre();
        assertEquals(4, actualBook.getId());
        assertEquals("The Casual Vacancy", actualBook.getName());
        assertEquals("Joanne Rowling", authors.getName());
        assertEquals("Comedy", genres.getName());

        actualBook = actualBookList.get(4);
        authors = actualBook.getAuthor();
        genres = actualBook.getGenre();
        assertEquals(5, actualBook.getId());
        assertEquals("Oliver Twist", actualBook.getName());
        assertEquals("Charles Dickens", authors.getName());
        assertEquals("Novel", genres.getName());

        actualBook = actualBookList.get(5);
        authors = actualBook.getAuthor();
        genres = actualBook.getGenre();
        assertEquals(6, actualBook.getId());
        assertEquals("The Mystery of Edwin Droods", actualBook.getName());
        assertEquals("Charles Dickens", authors.getName());
        assertEquals("Novel", genres.getName());

        actualBook = actualBookList.get(6);
        authors = actualBook.getAuthor();
        genres = actualBook.getGenre();
        assertEquals(7, actualBook.getId());
        assertEquals("David Copperfield", actualBook.getName());
        assertEquals("Charles Dickens", authors.getName());
        assertEquals("Novel", genres.getName());

    }

    @Test
    void bookUpdateBookNameTest() {
        Book expectedBook = new Book();
        expectedBook.setId(EXISTING_BOOK_ID);
        expectedBook.setName(NEW_BOOK_NAME);
        expectedBook.setAuthor(new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME));
        expectedBook.setGenre(new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        expectedBook.setComments(Collections.emptyList());
        bookDao.update(expectedBook);

        Book actualBook = em.find(Book.class, expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void bookUpdateBookGenreTest() {
        Book expectedBook = new Book();
        expectedBook.setId(EXISTING_BOOK_ID);
        expectedBook.setName(EXISTING_BOOK_NAME);
        expectedBook.setAuthor(new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME));
        expectedBook.setGenre(new Genre(NEW_GENRE_ID, NEW_GENRE_NAME));
        expectedBook.setComments(Collections.emptyList());
        bookDao.update(expectedBook);

        Book actualBook = em.find(Book.class, expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void bookUpdateBookAuthorTest() {
        Book expectedBook = new Book();
        expectedBook.setId(EXISTING_BOOK_ID);
        expectedBook.setName(EXISTING_BOOK_NAME);
        expectedBook.setAuthor(new Author(NEW_AUTHOR_ID, NEW_AUTHOR_NAME));
        expectedBook.setGenre(new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        expectedBook.setComments(Collections.emptyList());
        bookDao.update(expectedBook);
        Book actualBook = em.find(Book.class, expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void bookDeleteByIdTest() {
        Book book = em.find(Book.class, EXISTING_BOOK_ID);
        assertThat(book).isNotNull();
        em.detach(book);

        bookDao.deleteById(EXISTING_BOOK_ID);
        assertNull(em.find(Book.class, EXISTING_BOOK_ID));
    }

    @Test
    void getBookByAuthorIdTest() {
        List<Book> actualBook = bookDao.getByAuthorId(EXISTING_AUTHOR_ID);
        assertNotNull(actualBook);
    }
}