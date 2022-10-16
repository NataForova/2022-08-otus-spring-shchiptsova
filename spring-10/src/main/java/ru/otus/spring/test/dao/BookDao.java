package ru.otus.spring.test.dao;

import ru.otus.spring.test.domain.Book;

import java.util.List;

public interface BookDao {
    long count();
    Book save(Book book);
    Book getById(long id);
    List<Book> getByAuthorId(long authorId);
    List<Book> getByGenreId(long genreId);
    List<Book> getAll();
    Book update(Book book);
    void deleteById(long id);

}
