package ru.otus.spring.test.command;

import ru.otus.spring.test.domain.Book;

public interface ApplicationCommand {
    long countBooks();
    String insertBook(String bookNane, int authorId, int genreId);
    String getBookById(int bookId);
    String getBookByAuthorId(int authorId);
    String getBookByGenreId(int genreId);
    void deleteBook(int bookId);
    String getAllBooks();
    String updateBook(long id, String name, long author_id, long genre_id);
    String insertGenre(String name);
    String insertAuthor(String name);

}
