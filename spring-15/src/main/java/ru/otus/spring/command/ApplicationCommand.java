package ru.otus.spring.command;

import java.util.List;

public interface ApplicationCommand {
    long countBooks();
    String insertBook(String bookNane, String authorId, String genreId);
    String getBookById(String bookId);
    String getBookByAuthorId(String authorId);
    String getBookByGenreId(String genreId);
    void deleteBook(String bookId);
    String getAllBooks();
    String updateBook(String id, String name, String author_id, String genre_id);
    String insertGenre(String name);
    String insertAuthor(String name);
    String insertComment(String text, String bookId);
    String getAuthors();
    String getGenres();
    String updateComment(String commentId, String text);
}
