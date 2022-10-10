package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int count();
    int insert(String bookName, int authorId, int genreId);
    Book getById(int id);
    List<Book> getByAuthorId(int authorId);
    List<Book> getByGenreId(int genreId);
    List<Book> getAll();
    int update(long id, String name, long author_id, long genre_id);
    void deleteById(long id);

}
