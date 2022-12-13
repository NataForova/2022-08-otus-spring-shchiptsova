package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    public AuthorDaoJpa(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(a) " +
                        "from authors a",
                Long.class);
        return query.getSingleResult();
    }

    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Author getById(long id) {
        Optional<Author> optionalAuthor = Optional.ofNullable(em.find(Author.class, id));
        return optionalAuthor.orElse(null);
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from authors a", Author.class).getResultList();
    }

    @Override
    public Author update(Author author) {
        return save(author);
    }

    @Override
    public void deleteById(long id) {
        Author author = getById(id);
        if (author != null) {
            em.remove(author);
        }
    }
}
