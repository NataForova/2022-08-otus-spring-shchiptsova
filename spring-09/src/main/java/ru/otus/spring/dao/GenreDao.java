package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    void insert(Genre person);
    Genre getById(long id);
    List<Genre> getAll();
    Genre update(Genre person);
    void deleteById(long id);

}
