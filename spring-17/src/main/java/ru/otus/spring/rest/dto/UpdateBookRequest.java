package ru.otus.spring.rest.dto;

import lombok.Data;

@Data
public class UpdateBookRequest extends CreateBookRequest{
    private Long bookId;

    public UpdateBookRequest(Long bookId, String bookName, Long authorId, Long genreId) {
        super(bookName, authorId, genreId);
        this.bookId = bookId;
    }
}
