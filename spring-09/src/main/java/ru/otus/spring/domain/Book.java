package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {
    private Long id;
    private String name;
    private Author author;
    private Genre genre;
}
