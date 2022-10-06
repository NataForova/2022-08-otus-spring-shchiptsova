package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();
    void insert(Author person);
    Author getById(long id);
    List<Author> getAll();
    Author update(Author person);
    void deleteById(long id);
}
