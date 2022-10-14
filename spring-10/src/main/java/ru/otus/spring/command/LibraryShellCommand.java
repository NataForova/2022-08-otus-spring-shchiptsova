package ru.otus.spring.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

@ShellComponent
public class LibraryShellCommand implements ApplicationCommand {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public LibraryShellCommand(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    @ShellMethod(value = "Count books", key = {"count", "count-book"})
    public int countBooks() {
        return bookService.countBooks();
    }

    @Override
    @ShellMethod(value = "Insert book", key = {"i", "insert"})
    public int insertBook(String bookName, int authorId, int genreId) {
        return bookService.insertBook(bookName, authorId, genreId);
    }

    @Override
    @ShellMethod(value = "Get book by id", key = {"getById", "getBookById"})
    public String getBookById(int bookId) {
        return bookService.getBookById(bookId).toString();
    }

    @Override
    @ShellMethod(value = "Get books by author id", key = {"getByAuthorId", "getBookByAuthorId"})
    public String getBookByAuthorId(int authorId) {
        return bookService.getBookByAuthorId(authorId).toString();
    }

    @Override
    @ShellMethod(value = "Get books by genre id", key = {"getByGenreId", "getBookByGenreId"})
    public String getBookByGenreId(int genreId) {
        return bookService.getBookByGenreId(genreId).toString();
    }

    @Override
    @ShellMethod(value = "delete book", key = {"del", "deleteBook"})
    public void deleteBook(int bookId) {
        bookService.deleteBook(bookId);
    }

    @Override
    @ShellMethod(value = "get all books", key = {"getAll", "getAllBooks"})
    public String getAllBooks() {
        return bookService.getAllBooks().toString();
    }

    @Override
    @ShellMethod(value = "update book", key = {"u", "update"})
    public int updateBook(long id, String name, long author_id, long genre_id) {
        return bookService.updateBook(id, name, author_id, genre_id);
    }

    @Override
    @ShellMethod(value = "inset genre", key = {"insertGenre", "insertGenre"})
    public int insertGenre(String name) {
        return genreService.insert(name);
    }

    @Override
    @ShellMethod(value = "inset author", key = {"insertAuthor", "insertAuthor"})
    public int insertAuthor(String name) {
        return authorService.insert(name);
    }
}
