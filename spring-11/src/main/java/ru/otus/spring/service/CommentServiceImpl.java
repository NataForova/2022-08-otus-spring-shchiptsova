package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.CommentRepository;
import ru.otus.spring.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment insert(String text, Long bookId) {
        Comment comment = new Comment();
        comment.setCommentText(text);
        comment.setBookId(bookId);
        return commentRepository.save(comment);
    }
}
