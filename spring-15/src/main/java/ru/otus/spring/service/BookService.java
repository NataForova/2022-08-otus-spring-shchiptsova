package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {
    long countBooks();
    Book insertBook(String bookName, String authorId, String genreId);
    Book getBookById(String bookId);

    List<Book> getBookByAuthorId(String authorId);

    List<Book> getBookByGenreId(String genreId);

    void deleteBook(String bookId);
    List<Book> getAllBooks();
    Book updateBook(String id, String name, String author_id, String genre_id);
}
