package ru.otus.spring.test.dao;

import ru.otus.spring.test.domain.Genre;

import java.util.List;

public interface GenreDao {
    long count();
    Genre save(Genre genre);
    Genre getById(long id);
    List<Genre> getAll();
    Genre update(Genre genre);
    void deleteById(long id);

}
