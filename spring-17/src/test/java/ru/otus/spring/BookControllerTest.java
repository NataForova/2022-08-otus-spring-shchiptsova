package ru.otus.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.rest.BookController;
import ru.otus.spring.rest.dto.*;
import ru.otus.spring.service.BookService;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@ContextConfiguration(classes = ApplicationConfigTest.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;


    @MockBean
    private BookService bookService;


    @Test
    void shouldReturnCorrectBooksList() throws Exception {
        List<Book> books = List.of(
                new Book(1L, "Book1", new Author(), new Genre(), Collections.singletonList(new Comment(1L, 1L, "Тест"))),
                new Book(2L, "Book2", new Author(), new Genre(), Collections.singletonList(new Comment(2L, 2L, "Тест коммент"))));

                given(bookService.getAllBooks()).willReturn(books);

        List<BookDto> expectedResult = books.stream()
                .map(BookDto::toDto).collect(Collectors.toList());

        mvc.perform(get("/books/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnCorrectBookById() throws Exception {
        Book book =
                new Book(1L, "Book1", new Author(), new Genre(), Collections.singletonList(new Comment(1L, 1L, "Тест")));

        given(bookService.getBookById(1L)).willReturn(book);

        BookDto expectedResult = BookDto.toDto(book);

        mvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnCorrectListOfVariations() throws Exception {
        AvailableAuthorsAndGenres expectedResult  = new AvailableAuthorsAndGenres(
                List.of(new Author(1L, "Author 1", Collections.singletonList(new Book())), new Author(2L, "Author 2", Collections.singletonList(new Book()))),
                List.of(new Genre(1L, "Genre 1", Collections.singletonList(new Book())), new Genre(2L, "Genre 2", Collections.singletonList(new Book()))));

        given(bookService.getAvailableVariations()).willReturn(expectedResult);

        mvc.perform(get("/books/variations"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldCreateBook() throws Exception {
        Book expectedResult  = new Book(1L, "New book", new Author(1L, "Author 1", Collections.emptyList()),
        new Genre(1L, "Genre 1", Collections.emptyList()), Collections.singletonList(new Comment(1L, 1L, "Тест")));

        CreateBookRequest request = new CreateBookRequest("New book", 1L, 1L);

        given(bookService.insertBook(request.getBookName(), request.getAuthorId(), request.getGenreId())).willReturn(expectedResult);

        mvc.perform(post("/books/create").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        Book expectedResult  = new Book(1L, "New book2", new Author(1L, "Author 1", Collections.emptyList()),
                new Genre(1L, "Genre 1", Collections.emptyList()), Collections.singletonList(new Comment(1L, 1L, "Тест")));

        UpdateBookRequest request = new UpdateBookRequest(1L, "New book2", 1L, 1L);

        given(bookService.updateBook(request.getBookId(), request.getBookName(), request.getAuthorId(), request.getGenreId())).willReturn(expectedResult);

        mvc.perform(patch("/books/update").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldDeleteBook() throws Exception {
        mvc.perform(patch("/books/delete").content(asJsonString((new DeleteBooksRequest(List.of(1L, 2L))))).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void getBookByIdWhenIdIsWrong() throws Exception {
        given(bookService.getBookById(99L)).willReturn(null);

        mvc.perform(get("/books/99"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBookWhenAuthorIdIsWrong() throws Exception {
        CreateBookRequest request = new CreateBookRequest("New book", 99L, 1L);

        given(bookService.insertBook(request.getBookName(), request.getAuthorId(), request.getGenreId())).willThrow(AuthorNotFoundException.class);

        mvc.perform(post("/books/create").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBookWhenGenreIdIsWrong() throws Exception {
        CreateBookRequest request = new CreateBookRequest("New book", 1L, 99L);

        given(bookService.insertBook(request.getBookName(), request.getAuthorId(), request.getGenreId())).willThrow(GenreNotFoundException.class);

        mvc.perform(post("/books/create").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateBookWhenBookIdIsWrong() throws Exception {
        UpdateBookRequest request = new UpdateBookRequest(99L, "New book2", 1L, 1L);

        given(bookService.updateBook(request.getBookId(), request.getBookName(), request.getAuthorId(), request.getGenreId())).willThrow(IllegalArgumentException.class);

        mvc.perform(patch("/books/update").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateBookWhenAuthorIdIsWrong() throws Exception {
        UpdateBookRequest request = new UpdateBookRequest(1L, "New book2", 99L, 1L);

        given(bookService.updateBook(request.getBookId(), request.getBookName(), request.getAuthorId(), request.getGenreId())).willThrow(AuthorNotFoundException.class);

        mvc.perform(patch("/books/update").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateBookWhenGenreIdIsWrong() throws Exception {
        UpdateBookRequest request = new UpdateBookRequest(1L, "New book2", 1L, 99L);

        given(bookService.updateBook(request.getBookId(), request.getBookName(), request.getAuthorId(), request.getGenreId())).willThrow(GenreNotFoundException.class);

        mvc.perform(patch("/books/update").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
