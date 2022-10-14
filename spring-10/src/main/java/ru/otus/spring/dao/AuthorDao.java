package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();
    int insert(String authorName);
    Author getById(long id);
    List<Author> getAll();
    int update(Author author);
    void deleteById(long id);
}
