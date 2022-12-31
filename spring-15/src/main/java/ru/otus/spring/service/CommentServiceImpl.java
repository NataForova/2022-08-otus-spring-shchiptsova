package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.otus.spring.dao.CommentRepository;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;

import java.util.Collections;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment insert(String text, String bookId) {
        Assert.notNull(bookId, "Book id cannot be null");
        Assert.hasText(text, "Comment text cannot be null");

        Comment comment = new Comment();
        comment.setCommentText(text);
        comment.setBookId(bookId);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(String commentId, String newText) {
        Assert.notNull(commentId, "Comment id cannot be null");
        Assert.hasText(newText, "Comment text cannot be null");

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setCommentText(newText);
            return commentRepository.save(comment);
        } else {
            throw new CommentNotFoundException();
        }
    }
}
