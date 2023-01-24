package ru.otus.spring.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.otus.spring.dao.GenreRepository;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre insert(String name) {
        validateGenreName(name);
        Genre genre = new Genre();
        genre.setName(name);
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    private void validateGenreName(String genreName) {
        Assert.notNull(genreName, "Genre name cannot be null");
        Assert.isTrue(Strings.isNotEmpty(genreName) || Strings.isNotBlank(genreName), "Genre name cannot be empty");
    }
}
