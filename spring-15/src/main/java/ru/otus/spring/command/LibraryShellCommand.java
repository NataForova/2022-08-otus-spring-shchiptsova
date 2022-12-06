package ru.otus.spring.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

@ShellComponent
public class LibraryShellCommand implements ApplicationCommand {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    private final CommentService commentService;

    public LibraryShellCommand(BookService bookService,
                               AuthorService authorService,
                               GenreService genreService,
                               CommentService commentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
    }

    @Override
    @ShellMethod(value = "Count books", key = {"count", "count-book"})
    public long countBooks() {
        return bookService.countBooks();
    }

    @Override
    @ShellMethod(value = "Insert book", key = {"i", "insert"})
    public String insertBook(String bookName, int authorId, int genreId) {
        if (bookService.insertBook(bookName, authorId, genreId) == null) {
            return "Error during inserting book";
        }
        return "Book was inserted";
    }

    @Override
    @ShellMethod(value = "Get book by id", key = {"getById", "getBookById"})
    @Transactional
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
    @Transactional
    public String getAllBooks() {
        return bookService.getAllBooks().toString();
    }

    @Override
    @ShellMethod(value = "update book", key = {"u", "update"})
    public String updateBook(long id, String name, long author_id, long genre_id) {
        if (bookService.updateBook(id, name, author_id, genre_id) == null) {
            return "Error during updating book";
        }
        return "Book was updated";
    }

    @Override
    @ShellMethod(value = "inset genre", key = {"insertGenre", "insertGenre"})
    public String insertGenre(String name) {
        if (genreService.insert(name) == null) {
            return "Error during insert genre";
        }
        return "Genre was inserted";
    }

    @Override
    @ShellMethod(value = "inset author", key = {"insertAuthor", "insertAuthor"})
    public String insertAuthor(String name) {
        if (authorService.insert(name) == null) {
            return "Error during insert author";
        }
        return "Author was inserted";
    }

    @Override
    @ShellMethod(value = "inset comment", key = {"comment", "insetComment"})
    public String insertComment(String text, long bookId) {
        if (commentService.insert(text, bookId) == null) {
            return "Error during insert comment";
        }
        return "Comment was inserted";
    }
}
