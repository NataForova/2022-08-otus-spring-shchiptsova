package ru.otus.spring.test.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @SequenceGenerator(name = "commentIdGen", sequenceName = "COMMENT_ID_SEQUENCE", initialValue = 4, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentIdGen")
    private long id;

    @Column(name = "COMMENT_TEXT")
    private String commentText;
}
