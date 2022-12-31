package ru.otus.spring.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

import java.util.List;


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
    public String insertBook(String bookName, String authorId, String genreId) {
        if (bookService.insertBook(bookName, authorId, genreId) == null) {
            return "Error during inserting book";
        }
        return "Book was inserted";
    }

    @Override
    @ShellMethod(value = "Get book by id", key = {"getById", "getBookById"})
    public String getBookById(String bookId) {
        return bookService.getBookById(bookId).toString();
    }

    @Override
    @ShellMethod(value = "Get books by author id", key = {"getByAuthorId", "getBookByAuthorId"})
    public String getBookByAuthorId(String authorId) {
        return bookService.getBookByAuthorId(authorId).toString();
    }

    @Override
    @ShellMethod(value = "Get books by genre id", key = {"getByGenreId", "getBookByGenreId"})
    public String getBookByGenreId(String genreId) {
        return bookService.getBookByGenreId(genreId).toString();
    }

    @Override
    @ShellMethod(value = "delete book", key = {"del", "deleteBook"})
    public void deleteBook(String bookId) {
        bookService.deleteBook(bookId);
    }

    @Override
    @ShellMethod(value = "get all books", key = {"getAll", "getAllBooks"})
    public String getAllBooks() {
        return bookService.getAllBooks().toString();
    }

    @Override
    @ShellMethod(value = "update book", key = {"u", "update"})
    public String updateBook(String id, String name, String author_id, String genre_id) {
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
    public String insertComment(String text, String bookId) {
        if (commentService.insert(text, bookId) == null) {
            return "Error during insert comment";
        }
        return "Comment was inserted";
    }

    @Override
    @ShellMethod(value = "get authors", key = {"authors", "allAuthors"})
    public String getAuthors() {
        return authorService.getAll().toString();
    }

    @Override
    @ShellMethod(value = "get genres", key = {"genres", "allGenres"})
    public String getGenres() {
        return genreService.getAll().toString();
    }

    @Override
    @ShellMethod(value = "update comment", key = {"editComment", "editComment"})
    public String updateComment(String commentId, String text) {
        return commentService.update(commentId, text).toString();
    }
}
