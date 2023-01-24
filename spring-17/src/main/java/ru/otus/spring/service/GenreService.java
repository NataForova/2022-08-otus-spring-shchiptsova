package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre insert(String genreName);
    List<Genre> getAll();
}
