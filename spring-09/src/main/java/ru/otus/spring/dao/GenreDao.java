package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    int insert(String genreName);
    Genre getById(long id);
    List<Genre> getAll();
    int update(Genre genre);
    void deleteById(long id);

}
