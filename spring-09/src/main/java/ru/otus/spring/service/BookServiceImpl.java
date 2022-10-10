package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public int countBooks() {
        return bookDao.count();
    }

    @Override
    public int insertBook(String bookName, int authorId, int genreId) {
        return bookDao.insert(bookName, authorId, genreId);
    }

    @Override
    public Book getBookById(int bookId) {
        return bookDao.getById(bookId);
    }

    @Override
    public List<Book> getBookAuthorById(int authorId) {
        return bookDao.getByAuthorId(authorId);
    }

    @Override
    public List<Book> getBookByGenreId(int genreId) {
        return bookDao.getByGenreId(genreId);
    }

    @Override
    public void deleteBook(int bookId) {
        bookDao.deleteById(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public int updateBook(long id, String name, long author_id, long genre_id) {
        return bookDao.update(id, name, author_id, genre_id);
    }
}
