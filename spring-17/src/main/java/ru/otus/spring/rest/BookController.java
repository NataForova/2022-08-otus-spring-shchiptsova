package ru.otus.spring.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.AvailableAuthorsAndGenres;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.rest.dto.CreateBookRequest;
import ru.otus.spring.rest.dto.UpdateBookRequest;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;


    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/getAll")
    public ResponseEntity<List<BookDto>> getBookList() {
        List<BookDto> books = bookService.getAllBooks()
                .stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<BookDto> getBookByIdt(@PathVariable Long id) {
        BookDto book = BookDto.toDto(bookService.getBookById(id));
        return ResponseEntity.ok(book);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/variations")
    public ResponseEntity<AvailableAuthorsAndGenres> getAvailableAuthorsAndGenres() {
        List<Author> authors = authorService.getAll();
        List<Genre> genres = genreService.getAll();
        return ResponseEntity.ok(new AvailableAuthorsAndGenres(authors, genres));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/create")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest request) {
        return ResponseEntity.ok(bookService.insertBook(request.getBookName(),
                request.getAuthorId(),
                request.getGenreId()));
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/update")
    public ResponseEntity<Book> updateBook(@RequestBody UpdateBookRequest request) {
        return ResponseEntity.ok(bookService.updateBook(request.getBookId(), request.getBookName(),
                request.getAuthorId(),
                request.getGenreId()));
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/edit/{id}")
    public ResponseEntity<Book> editBook(@RequestBody CreateBookRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(bookService.updateBook(id, request.getBookName(),
                request.getAuthorId(),
                request.getGenreId()));
    }
}
