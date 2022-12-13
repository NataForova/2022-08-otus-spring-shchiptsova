package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;

public interface CommentService {
    Comment insert(String text, String bookId);
    Comment update(String commentId, String newText);
}
