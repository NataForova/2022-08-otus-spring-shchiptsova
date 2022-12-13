package ru.otus.spring.dao;

import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentDao {
    long count();
    Comment save(Comment comment);
    Comment getById(long id);
    List<Comment> getAll();
    Comment update(Comment comment);
    void deleteById(long id);
}
