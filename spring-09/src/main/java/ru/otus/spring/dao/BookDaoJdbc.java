package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;


    public BookDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return namedParameterJdbcTemplate.queryForObject("select count(*) from books", new HashMap<>(), Integer.class);
    }

    @Override
    public int insert(String bookName, int authorId, int genreId) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", 999);
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
        return namedParameterJdbcTemplate.queryForObject("select id, name, author_id, genre_id from books where id = :id",
                namedParameters, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int authorId = resultSet.getInt("author_id");
            int genreId = resultSet.getInt("genre_id");

            return new Book(id, name, authorId, genreId);
        }
    }
}
