package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {
    long count();
    Book save(Book book);
    Book getById(long id);
    List<Book> getAll();
    Book update(Book book);
    void deleteById(long id);

}
