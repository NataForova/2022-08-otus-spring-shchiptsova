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
        Book book = bookDao.getById(bookId);
        return bookDao.getById(bookId).toString();
    }
}
