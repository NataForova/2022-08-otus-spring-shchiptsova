package java.ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.GenreRepository;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(GenreRepository.class)
public class GenreDaoTest {

    private static final long EXPECTED_GENRE_COUNT = 5L;
    private static final long EXISTING_GENRE_ID = 1L;
    private static final String EXISTING_GENRE_NAME = "Fantasy";
    private static final String NEW_GENRE_NAME = "Fairy tale";
    private static final long NEW_GENRE_ID = 6L;

    @Autowired
    GenreRepository genreRepository;

    @Test
    void genreCountTest() {
        assertEquals(EXPECTED_GENRE_COUNT, genreRepository.count());
    }

    @Test
    void genreInsertTest() {
        Genre expectedGenre = new Genre();
        expectedGenre.setName(NEW_GENRE_NAME);
        genreRepository.save(expectedGenre);

        Genre actualGenre = genreRepository.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(actualGenre);
    }

    @Test
    void genreGetByIdTest() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreRepository.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(actualGenre);
    }

    @Test
    void genreGetAllTest() {
        List<Genre> actualGenreList = genreRepository.findAll();

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
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, NEW_GENRE_NAME);
        genreRepository.save(expectedGenre);

        Genre actualGenre = genreRepository.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(actualGenre);
    }

    @Test
    void genreDeleteByIdTest() {
        assertThatCode(() -> genreRepository.getById(EXISTING_GENRE_ID))
                .doesNotThrowAnyException();

        genreRepository.deleteById(EXISTING_GENRE_ID);

        assertNull(genreRepository.getById(EXISTING_GENRE_ID));
    }
}
