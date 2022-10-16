package ru.otus.spring.test.dao;

import ru.otus.spring.test.domain.Author;

import java.util.List;

public interface AuthorDao {
    long count();
    Author save(Author author);
    Author getById(long id);
    List<Author> getAll();
    Author update(Author author);
    void deleteById(long id);
}
