package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public BookDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int count() {
        return namedParameterJdbcTemplate.queryForObject("select count(*) from books", new HashMap<>(), Integer.class);
    }

    @Override
    public int insert(String bookName, int authorId, int genreId) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", getLastBookId() + 1);
        namedParameters.put("name", bookName);
        namedParameters.put("author", authorId);
        namedParameters.put("genre", genreId);

        return namedParameterJdbcTemplate.update("insert into books (id, name, author_id, genre_id) values(:id, :name, :author, :genre)",
                namedParameters);
    }

    @Override
    public Book getById(int id) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(
                "select books.id, books.name, author_id, genre_id, authors.name, genres.name " +
                        "from books left join authors on books.author_id = authors.id" +
                        " left join genres on books.genre_id=genres.id " +
                        "where books.id = :id",
                namedParameters, new BookMapper());
    }

    @Override
    public List<Book> getByAuthorId(int authorId) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("author_id", authorId);
        return namedParameterJdbcTemplate.query(
                "select books.id, books.name, author_id, genre_id, authors.name, genres.name " +
                        "from books left join authors on books.author_id = authors.id" +
                        " left join genres on books.genre_id=genres.id " +
                        "where authors.id = :author_id",
                namedParameters, new BookMapper());
    }

    @Override
    public List<Book> getByGenreId(int genreId) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("genre_id", genreId);
        return namedParameterJdbcTemplate.query(
                "select books.id, books.name, author_id, genre_id, authors.name, genres.name " +
                        "from books left join authors on books.author_id = authors.id" +
                        " left join genres on books.genre_id=genres.id " +
                        "where genres.id = :genre_id",
                namedParameters, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcTemplate.query(
                "select books.id, books.name, author_id, genre_id, authors.name, genres.name " +
                        "from books left join authors on books.author_id = authors.id" +
                        " left join genres on books.genre_id=genres.id ",  new HashMap<>(), new BookMapper() );
    }

    @Override
    public int update(long id, String name, long author_id, long genre_id) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        namedParameters.put("name", name);
        namedParameters.put("author_id", author_id);
        namedParameters.put("genre_id", genre_id);

        return namedParameterJdbcTemplate.update("update books set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id)",
                namedParameters);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        namedParameterJdbcTemplate.update("delete from books where id = :id", namedParameters);
    }

    private int getLastBookId() {
        return namedParameterJdbcTemplate.queryForObject("select max(id) from books", new HashMap<>(), Integer.class);
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

            return new Book(id, bookName, author, genre);
        }
    }

}
