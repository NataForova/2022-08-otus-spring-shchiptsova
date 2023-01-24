package ru.otus.spring.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.rest.dto.*;
import ru.otus.spring.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
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
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            BookDto bookDto = BookDto.toDto(bookService.getBookById(id));
            return ResponseEntity.ok(bookDto);
        } else {
            return ResponseEntity.badRequest().body(new BookDto());
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/variations")
    public ResponseEntity<AvailableAuthorsAndGenres> getAvailableAuthorsAndGenres() {
        return ResponseEntity.ok(bookService.getAvailableVariations());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/create")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest request) {
        try {
            return ResponseEntity.ok(bookService.insertBook(request.getBookName(),
                    request.getAuthorId(),
                    request.getGenreId()));
        } catch (AuthorNotFoundException | GenreNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/update")
    public ResponseEntity<Book> updateBook(@RequestBody UpdateBookRequest request) {
        try {
            return ResponseEntity.ok(bookService.updateBook(request.getBookId(), request.getBookName(),
                    request.getAuthorId(),
                    request.getGenreId()));
        } catch (AuthorNotFoundException | GenreNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PatchMapping( consumes = MediaType.APPLICATION_JSON_VALUE, value = "delete")
    public ResponseEntity<String> deleteBook(@RequestBody DeleteBooksRequest request) {
        bookService.deleteBooks(request.getBookIds());
        return ResponseEntity.ok().body("OK");
    }
}
