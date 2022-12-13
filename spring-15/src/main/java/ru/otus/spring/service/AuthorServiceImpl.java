package ru.otus.spring.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.otus.spring.dao.AuthorRepository;
import ru.otus.spring.domain.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author insert(String name) {
        validateAuthorName(name);
        Author author = new Author();
        author.setName(name);
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    private void validateAuthorName(String authorName) {
        Assert.notNull(authorName, "Author name cannot be null");
        Assert.isTrue(Strings.isNotEmpty(authorName) || Strings.isNotBlank(authorName), "Author name cannot be empty");
    }
}
