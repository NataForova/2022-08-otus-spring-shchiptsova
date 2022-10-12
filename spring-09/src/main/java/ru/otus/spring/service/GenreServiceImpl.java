package ru.otus.spring.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.otus.spring.dao.GenreDao;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public int insert(String name) {
        validateGenreName(name);
        return genreDao.insert(name);
    }

    private void validateGenreName(String genreName) {
        Assert.notNull(genreName, "Genre name cannot be null");
        Assert.isTrue(Strings.isNotEmpty(genreName) || Strings.isNotBlank(genreName), "Genre name cannot be empty");
    }
}
