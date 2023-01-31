package ru.otus.spring.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.AuthorRepository;
import ru.otus.spring.dao.BookRepository;
import ru.otus.spring.dao.GenreRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;


    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/getAll")
    public Flux<BookDto> getBookList() {
        return bookRepository.findAll()
                .map(BookDto::toDto);
    }

   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public Mono<BookDto> getBookById(@PathVariable String id) {
        return bookRepository.findById(id).map(BookDto::toDto);
        /*if (optionalBook.isPresent()) {
            BookDto bookDto = BookDto.toDto(optionalBook.get());
            return ResponseEntity.ok(bookDto);
        } else {
            return ResponseEntity.badRequest().body(new BookDto());
        }*/

    }

   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/variations")
    public Mono<AvailableAuthorsAndGenres> getAvailableAuthorsAndGenres() {
        Flux<Author> authorList = authorRepository.findAll();
        Flux<Genre> genreList = genreRepository.findAll();
        return Mono.just( new AvailableAuthorsAndGenres(authorList, genreList));
    }

    /*@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/create")
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
*/
    @PatchMapping( consumes = MediaType.APPLICATION_JSON_VALUE, value = "delete")
    public Mono<String> deleteBook(@RequestBody DeleteBooksRequest request) {
        for (String bookId: request.getBookIds()) {
            bookRepository.deleteById(bookId);
        }
        return Mono.just("OK");
    }
}
