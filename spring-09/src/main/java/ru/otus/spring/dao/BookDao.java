package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int count();
    void insert(Book person);
    Book getById(long id);
    List<Book> getAll();
    Book update(Book person);
    void deleteById(long id);

}
