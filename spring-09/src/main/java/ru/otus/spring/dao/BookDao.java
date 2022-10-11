package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int count();
    int insert(String bookName, long authorId, long genreId);
    Book getById(long id);
    List<Book> getByAuthorId(long authorId);
    List<Book> getByGenreId(long genreId);
    List<Book> getAll();
    int update(long id, String name, long author_id, long genre_id);
    void deleteById(long id);

}
