package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Data
@AllArgsConstructor
public class AvailableAuthorsAndGenres {
    private List<Author> authors;
    private List<Genre> genres;


}
