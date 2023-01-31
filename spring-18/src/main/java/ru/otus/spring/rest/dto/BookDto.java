package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.stream.Collectors;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookDto {
    private String id;
    private String name;
    private String authorName;
    private String genreName;
    private String comment;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(),
                book.getName(),
                book.getAuthor().getName(),
                book.getGenre().getName(),
                book.getComments().stream()
                        .map(Comment::getCommentText)
                        .collect(Collectors.joining(", ")));
    }
}
