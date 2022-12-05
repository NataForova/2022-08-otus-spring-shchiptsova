package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface AuthorDao {
    long count();
    Author save(Author author);
    Author getById(long id);
    List<Author> getAll();
    List<Book> getBooksByGenreAuthorId(long id);
    Author update(Author author);
    void deleteById(long id);
}
