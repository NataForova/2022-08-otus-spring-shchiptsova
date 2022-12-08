package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    long count();
    Genre save(Genre genre);
    Genre getById(long id);
    List<Genre> getAll();
    Genre update(Genre genre);
    void deleteById(long id);

}
