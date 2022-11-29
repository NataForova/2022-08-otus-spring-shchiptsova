package ru.otus.spring.test.service;

import ru.otus.spring.test.domain.Comment;
import ru.otus.spring.test.domain.Genre;

public interface CommentService {
    Comment insert(String text, Long bookId);
}
