package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    public GenreDaoJpa(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public long count() {
        return em.createQuery("select count(g) " +
                        "from genres g",
                Long.class).getSingleResult();
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == null) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Genre getById(long id) {
        Optional<Genre> optionalGenre = Optional.ofNullable(em.find(Genre.class, id));
        return optionalGenre.orElse(null);
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("select g from genres g", Genre.class).getResultList();
    }

    @Override
    public List<Book> getBooksByGenreId(long id) {
        Query query =  em.createQuery("select g.books from genres g where g.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Genre update(Genre genre) {
        return save(genre);
    }

    @Override
    public void deleteById(long id) {
        Genre genre = getById(id);
        if (genre != null) {
            em.remove(genre);
        }
    }
}
