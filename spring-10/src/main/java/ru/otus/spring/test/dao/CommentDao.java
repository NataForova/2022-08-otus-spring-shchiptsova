package ru.otus.spring.test.dao;

import ru.otus.spring.test.domain.Comment;
import ru.otus.spring.test.domain.Genre;

import java.util.List;

public interface CommentDao {
    long count();
    Comment save(Comment comment);
    Comment getById(long id);
    List<Comment> getAll();
    Comment update(Comment comment);
    void deleteById(long id);
}
