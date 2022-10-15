package ru.otus.spring.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.exception.GenreNotFoundException;

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
    public int countBooks() {
        return bookDao.count();
    }

    @Override
    public int insertBook(String bookName, long authorId, long genreId) {
        validateBookName(bookName);
        validateAuthorId(authorId);
        validateGenreId(genreId);
        return bookDao.insert(bookName, authorId, genreId);
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
    public void deleteBook(long bookId) {
        bookDao.deleteById(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public int updateBook(long id, String bookName, long authorId, long genreId) {
        validateBookName(bookName);
        validateAuthorId(authorId);
        validateGenreId(genreId);
        return bookDao.update(id, bookName, authorId, genreId);
    }

    private void validateBookName(String bookName) {
        Assert.notNull(bookName, "Book name cannot be null");
        Assert.isTrue(Strings.isNotEmpty(bookName) || Strings.isNotBlank(bookName), "Book name cannot be empty");
    }

    private void validateAuthorId(long authorId) {
        try {
            authorDao.getById(authorId);
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorNotFoundException(e);
        }
    }

    private void validateGenreId(long genreId) {
        try {
            genreDao.getById(genreId);
        } catch (EmptyResultDataAccessException e) {
            throw new GenreNotFoundException(e);
        }
    }

}
