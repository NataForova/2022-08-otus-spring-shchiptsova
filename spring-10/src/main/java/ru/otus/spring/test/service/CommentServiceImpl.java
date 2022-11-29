package ru.otus.spring.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.test.dao.CommentDao;
import ru.otus.spring.test.dao.GenreDao;
import ru.otus.spring.test.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public Comment insert(String text, Long bookId) {
        Comment comment = new Comment();
        comment.setCommentText(text);
        comment.setBookId(bookId);
        return commentDao.save(comment);
    }
}
