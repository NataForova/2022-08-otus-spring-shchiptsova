package ru.otus.spring.test.dao;

import org.hibernate.jpa.QueryHints;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.test.domain.Author;
import ru.otus.spring.test.domain.Book;
import ru.otus.spring.test.domain.Genre;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@Transactional
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
                Long.class).getSingleResult();    }

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
        Optional<Book> optionalBook = Optional.ofNullable(em.find(Book.class, id));
        return optionalBook.orElse(null);
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
                        "from books b " +
                        "where genres.id= :genreId",
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
        Query query = em.createQuery("delete " +
                "from books b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
