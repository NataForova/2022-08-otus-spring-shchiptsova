package ru.otus.spring.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

@ShellComponent
public class BooksShellCommand implements ApplicationCommand {
    //TODO перенести в сервис
    private final BookDao bookDao;

    public BooksShellCommand(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @ShellMethod(value = "Count books", key = {"count", "count-book"})
    public int countBooks() {
        return bookDao.count();
    }

    @Override
    @ShellMethod(value = "Insert book", key = {"i", "insert"})
    public int insertBook(String bookName, int authorId, int genreId) {
        return bookDao.insert(bookName, authorId, genreId);
    }

    @Override
    @ShellMethod(value = "Get book", key = {"getBook", "getBook"})
    public String getBookById(int bookId) {
        return bookDao.getById(bookId).toString();
    }

    @Override
    @ShellMethod(value = "delete book", key = {"del", "deleteBook"})
    public void deleteBook(int bookId) {
        bookDao.deleteById(bookId);
    }

    @Override
    @ShellMethod(value = "get all books", key = {"getAll", "getAllBooks"})
    public String getAllBooks() {
        return bookDao.getAll().toString();
    }

    @Override
    @ShellMethod(value = "update book", key = {"update", "update"})
    public int updateBook(long id, String name, long author_id, long genre_id){
        return bookDao.update(id, name, author_id, genre_id);
    }


}
