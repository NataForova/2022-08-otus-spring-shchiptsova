package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBookRequest {
    private String bookName;
    private Long authorId;
    private Long genreId;

    public CreateBookRequest(String bookName, Long authorId, Long genreId) {
        this.bookName = bookName;
        this.authorId = authorId;
        this.genreId = genreId;
    }
}
