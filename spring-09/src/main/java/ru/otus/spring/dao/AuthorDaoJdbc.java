package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public class AuthorDaoJdbc implements AuthorDao {
    @Override
    public int count() {
        return 0;
    }

    @Override
    public void insert(Author person) {

    }

    @Override
    public Author getById(long id) {
        return null;
    }

    @Override
    public List<Author> getAll() {
        return null;
    }

    @Override
    public Author update(Author person) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
