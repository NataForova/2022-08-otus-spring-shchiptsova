package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comments")
@Data
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", commentText='" + commentText + '\'' +
                '}' + '\n';
    }
}
