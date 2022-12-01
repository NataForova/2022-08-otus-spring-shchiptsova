package ru.otus.spring.test.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.otus.spring.test.dao.AuthorDao;
import ru.otus.spring.test.dao.BookDao;
import ru.otus.spring.test.dao.GenreDao;
import ru.otus.spring.test.domain.Author;
import ru.otus.spring.test.domain.Book;
import ru.otus.spring.test.domain.Genre;
import ru.otus.spring.test.exception.AuthorNotFoundException;
import ru.otus.spring.test.exception.GenreNotFoundException;

import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public long countBooks() {
        return bookDao.count();
    }

    @Override
    @Transactional
    public Book insertBook(String bookName, long authorId, long genreId) {
        validateBookName(bookName);
        Author author = validateAuthorId(authorId);
        Genre genre = validateGenreId(genreId);
        Book book = new Book();
        book.setName(bookName);
        book.setGenre(genre);
        book.setAuthor(author);

        return bookDao.save(book);
    }

    @Override
    public Book getBookById(long bookId) {
        return bookDao.getById(bookId);
    }

    @Override
    public List<Book> getBookByAuthorId(long authorId) {
        validateAuthorId(authorId);
        return bookDao.getByAuthorId(authorId);
    }

    @Override
    public List<Book> getBookByGenreId(long genreId) {
        validateGenreId(genreId);
        return bookDao.getByGenreId(genreId);
    }

    @Override
    @Transactional
    public void deleteBook(long bookId) {
        bookDao.deleteById(bookId);
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    @Transactional
    public Book updateBook(long id, String bookName, long authorId, long genreId) {
        validateBookName(bookName);
        Author author = validateAuthorId(authorId);
        Genre genre = validateGenreId(genreId);
        Book book = new Book(id, bookName, author, genre, Collections.emptyList());

        return bookDao.update(book);
    }

    private void validateBookName(String bookName) {
        Assert.notNull(bookName, "Book name cannot be null");
        Assert.isTrue(Strings.isNotEmpty(bookName) || Strings.isNotBlank(bookName), "Book name cannot be empty");
    }

    private Author validateAuthorId(long authorId) {
        Author author = authorDao.getById(authorId);
        if (author == null) {
            throw new AuthorNotFoundException();
        }
        return author;
    }

    private Genre validateGenreId(long genreId) {
        Genre genre = genreDao.getById(genreId);
        if (genre == null) {
            throw new GenreNotFoundException();
        }
        return genre;

    }

}
