package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthorDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int count() {
        return namedParameterJdbcTemplate.queryForObject("select count(*) from authors", new HashMap<>(), Integer.class);
    }

    @Override
    public int insert(String authorName) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", getLastAuthorId() + 1);
        namedParameters.put("name", authorName);

        return namedParameterJdbcTemplate.update("insert into authors (id, name) values(:id, :name)",
                namedParameters);
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(
                "select authors.id, authors.name " +
                        "from authors where authors.id = :id ",  namedParameters, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcTemplate.query(
                "select authors.id, authors.name " +
                        "from authors ",  new HashMap<>(), new AuthorMapper());
    }

    @Override
    public int update(Author author) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", author.getId());
        namedParameters.put("name", author.getName());

        return namedParameterJdbcTemplate.update("update authors set name = :name where id = :id",
                namedParameters);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        namedParameterJdbcTemplate.update("delete from authors where id = :id", namedParameters);
    }

    private int getLastAuthorId() {
        return namedParameterJdbcTemplate.queryForObject("select max(id) from authors", new HashMap<>(), Integer.class);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
