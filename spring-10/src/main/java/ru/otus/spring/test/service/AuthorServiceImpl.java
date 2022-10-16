package ru.otus.spring.test.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.otus.spring.test.dao.AuthorDao;
import ru.otus.spring.test.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author insert(String name) {
        validateAuthorName(name);
        Author author = new Author();
        author.setName(name);
        return authorDao.save(author);
    }

    private void validateAuthorName(String authorName) {
        Assert.notNull(authorName, "Author name cannot be null");
        Assert.isTrue(Strings.isNotEmpty(authorName) || Strings.isNotBlank(authorName), "Author name cannot be empty");
    }
}
