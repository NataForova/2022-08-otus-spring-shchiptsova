package ru.otus.spring.rest.dto;

import lombok.Data;

@Data
public class UpdateBookRequest extends CreateBookRequest{
    private String bookId;

    public UpdateBookRequest(String bookId, String bookName, String authorId, String genreId) {
        super(bookName, authorId, genreId);
        this.bookId = bookId;
    }
}
