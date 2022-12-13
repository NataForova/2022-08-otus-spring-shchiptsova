package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorService {
    Author insert(String name);
    List<Author> getAll();
}
