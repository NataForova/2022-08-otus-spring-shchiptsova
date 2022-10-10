package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {
    int countBooks();
    int insertBook(String bookName, int authorId, int genreId);
    Book getBookById(int bookId);

    List<Book> getBookAuthorById(int authorId);

    List<Book> getBookByGenreId(int genreId);

    void deleteBook(int bookId);
    List<Book> getAllBooks();
    int updateBook(long id, String name, long author_id, long genre_id);
}
