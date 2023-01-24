package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comments")
@Table(name = "comments")
public class Comment {
    @Id
    @SequenceGenerator(name = "commentIdGen", sequenceName = "COMMENT_ID_SEQUENCE", initialValue = 4, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentIdGen")
    private Long id;

    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "COMMENT_TEXT")
    private String commentText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
