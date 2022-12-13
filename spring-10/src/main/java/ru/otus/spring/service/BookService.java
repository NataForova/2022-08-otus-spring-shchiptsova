package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {
    long countBooks();
    Book insertBook(String bookName, long authorId, long genreId);
    Book getBookById(long bookId);

    List<Book> getBookByAuthorId(long authorId);

    List<Book> getBookByGenreId(long genreId);

    void deleteBook(long bookId);
    List<Book> getAllBooks();
    Book updateBook(long id, String name, long author_id, long genre_id);
}
