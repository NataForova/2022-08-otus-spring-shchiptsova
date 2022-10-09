package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Book {
    private Long id;
    private String name;
    private int author_id;
    private int genre_id;
    //private Author author;
    //private Genre genre;
}
