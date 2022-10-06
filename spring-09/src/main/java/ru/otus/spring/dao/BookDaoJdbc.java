package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public class BookDaoJdbc implements BookDao {
    @Override
    public int count() {
        return 0;
    }

    @Override
    public void insert(Book person) {

    }

    @Override
    public Book getById(long id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book update(Book person) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
