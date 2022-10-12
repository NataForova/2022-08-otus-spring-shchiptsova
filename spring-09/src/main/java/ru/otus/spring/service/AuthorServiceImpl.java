package ru.otus.spring.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.otus.spring.dao.AuthorDao;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public int insert(String name) {
        validateAuthorName(name);
        return authorDao.insert(name);
    }

    private void validateAuthorName(String authorName) {
        Assert.notNull(authorName, "Author name cannot be null");
        Assert.isTrue(Strings.isNotEmpty(authorName) || Strings.isNotBlank(authorName), "Author name cannot be empty");
    }
}
