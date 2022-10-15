package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Book {
    private Long id;
    private String name;
    private Author author;
    private Genre genre;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author.toString() +
                ", genre=" + genre.toString() +
                "}\n";
    }
}
