package ru.otus.spring.test.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.test.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private final EntityManager em;


    public BookDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public long count() {
        return em.createQuery("select count(b) " +
                        "from books b",
                Long.class).getSingleResult();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Book getById(long id) {
        TypedQuery<Book> query = em.createQuery("select b " +
                "from books b join fetch b.comments c " +
                "where b.id = :id", Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getByAuthorId(long authorId) {
        TypedQuery<Book> query = em.createQuery("select b " +
                        "from books b join fetch  b.author a " +
                        "where a.id = :authorId",
                Book.class);
        query.setParameter("authorId", authorId);
        return query.getResultList();
    }

    @Override
    public List<Book> getByGenreId(long genreId) {
        TypedQuery<Book> query = em.createQuery("select b " +
                        "from books b join fetch  b.genre g " +
                        "where g.id= :genreId",
                Book.class);
        query.setParameter("genreId", genreId);
        return query.getResultList();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from books b", Book.class);
        return query.getResultList();
    }

    @Override
    public Book update(Book book) {
        return save(book);
    }

    @Override
    public void deleteById(long id) {
        Book book = em.find(Book.class, id);
        if (book != null) {
            em.remove(book);
        }
    }
}
