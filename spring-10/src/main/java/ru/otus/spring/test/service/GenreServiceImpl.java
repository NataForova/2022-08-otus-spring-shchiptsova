package ru.otus.spring.test.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.otus.spring.test.dao.GenreDao;
import ru.otus.spring.test.domain.Genre;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre insert(String name) {
        validateGenreName(name);
        Genre genre = new Genre();
        genre.setName(name);
        return genreDao.save(genre);
    }

    private void validateGenreName(String genreName) {
        Assert.notNull(genreName, "Genre name cannot be null");
        Assert.isTrue(Strings.isNotEmpty(genreName) || Strings.isNotBlank(genreName), "Genre name cannot be empty");
    }
}
