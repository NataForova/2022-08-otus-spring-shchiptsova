package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int count();
    public int insert(String bookName, int authorId, int genreId);
    Book getById(int id);
    List<Book> getAll();
    Book update(Book person);
    void deleteById(long id);

}
