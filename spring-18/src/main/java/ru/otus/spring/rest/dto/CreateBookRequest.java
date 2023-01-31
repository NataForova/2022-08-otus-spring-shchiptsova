package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBookRequest {
    private String bookName;
    private String authorId;
    private String genreId;

    public CreateBookRequest(String bookName, String authorId, String genreId) {
        this.bookName = bookName;
        this.authorId = authorId;
        this.genreId = genreId;
    }
}
