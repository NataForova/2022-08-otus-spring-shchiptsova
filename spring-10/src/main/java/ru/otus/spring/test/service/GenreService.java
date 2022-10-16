package ru.otus.spring.test.service;

import ru.otus.spring.test.domain.Genre;

public interface GenreService {
    Genre insert(String genreName);
}
