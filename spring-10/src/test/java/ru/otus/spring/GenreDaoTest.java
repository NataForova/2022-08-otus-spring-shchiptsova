package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.dao.GenreDaoJpa;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@ContextConfiguration(classes = TestApplicationConfig.class)
@Import(GenreDaoJpa.class)
public class GenreDaoTest {

    private static final long EXPECTED_GENRE_COUNT = 5L;
    private static final long EXISTING_GENRE_ID = 1L;
    private static final String EXISTING_GENRE_NAME = "Fantasy";
    private static final String NEW_GENRE_NAME = "Fairy tale";
    private static final long NEW_GENRE_ID = 6L;

    @Autowired
    GenreDao genreDao;

    @Test
    void genreCountTest() {
        assertEquals(EXPECTED_GENRE_COUNT, genreDao.count());
    }

    @Test
    void genreInsertTest() {
        Genre expectedGenre = new Genre();
        expectedGenre.setName(NEW_GENRE_NAME);
        genreDao.save(expectedGenre);

        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(actualGenre);
    }

    @Test
    void genreGetByIdTest() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME, new ArrayList<>());
        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(actualGenre);
    }

    @Test
    void genreGetAllTest() {
        List<Genre> actualGenreList = genreDao.getAll();

        assertEquals(EXPECTED_GENRE_COUNT, actualGenreList.size());

        Genre actualGenre = actualGenreList.get(0);
        assertEquals(1, actualGenre.getId());
        assertEquals("Fantasy", actualGenre.getName());

        actualGenre = actualGenreList.get(1);
        assertEquals(2, actualGenre.getId());
        assertEquals("Comedy", actualGenre.getName());

        actualGenre = actualGenreList.get(2);
        assertEquals(3, actualGenre.getId());
        assertEquals("Science fiction", actualGenre.getName());

        actualGenre = actualGenreList.get(3);
        assertEquals(4, actualGenre.getId());
        assertEquals("Detective", actualGenre.getName());

        actualGenre = actualGenreList.get(4);
        assertEquals(5, actualGenre.getId());
        assertEquals("Novel", actualGenre.getName());

    }

    @Test
    void genreUpdateTest() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, NEW_GENRE_NAME, new ArrayList<>());
        genreDao.update(expectedGenre);

        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(actualGenre);
    }

    @Test
    void genreDeleteByIdTest() {
        assertThatCode(() -> genreDao.getById(EXISTING_GENRE_ID))
                .doesNotThrowAnyException();

        genreDao.deleteById(EXISTING_GENRE_ID);

        assertNull(genreDao.getById(EXISTING_GENRE_ID));
    }
}
