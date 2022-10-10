package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GenreDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int count() {
        return namedParameterJdbcTemplate.queryForObject("select count(*) from genres", new HashMap<>(), Integer.class);
    }

    @Override
    public int insert(String genreName) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", getLastGenreId() + 1);
        namedParameters.put("name",genreName);

        return namedParameterJdbcTemplate.update("insert into genres (id, name) values(:id, :name)",
                namedParameters);
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(
                "select genres.id, genres.name " +
                        "from genres where genres.id = :id ",  new HashMap<>(), Genre.class);
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcTemplate.query(
                "select genres.id, genres.name " +
                        "from genres ",  new HashMap<>(), new GenreMapper());
    }

    @Override
    public int update(Genre genre) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", genre.getId());
        namedParameters.put("name", genre.getName());

        return namedParameterJdbcTemplate.update("update genres set name = :name where id = :id)",
                namedParameters);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        namedParameterJdbcTemplate.update("delete from genres where id = :id", namedParameters);
    }

    private int getLastGenreId() {
        return namedParameterJdbcTemplate.queryForObject("select max(id) from genres", new HashMap<>(), Integer.class);
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
