package ru.otus.spring.test.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.spring.test.domain.Author;
import ru.otus.spring.test.domain.Book;
import ru.otus.spring.test.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
                        "from books b " +
                        "where authors.id = :authorId",
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
        return em.createQuery("select a from authors a", Book.class).getResultList();
    }

    @Override
    public Book update(Book book) {
        return save(book);
    }

    @Override
    public void deleteById(long id) {
        Book book = getById(id);
        if (book != null) {
            em.remove(book);
        }
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String bookName = resultSet.getString("name");
            long authorId = resultSet.getInt("author_id");
            String authorName = resultSet.getString("authors.name");
            long genreId = resultSet.getInt("genre_id");
            String genreName = resultSet.getString("genres.name");

            Author author = new Author(authorId, authorName);
            Genre genre = new Genre(genreId, genreName);

            return new Book(id, bookName, Collections.singletonList(author), Collections.singletonList(genre));
        }
    }

}
