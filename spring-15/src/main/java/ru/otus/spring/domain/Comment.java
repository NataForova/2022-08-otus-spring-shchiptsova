package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    private String bookId;

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
