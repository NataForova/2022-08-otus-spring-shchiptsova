package ru.otus.spring.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.otus.spring.dao.AuthorRepository;
import ru.otus.spring.dao.BookRepository;
import ru.otus.spring.dao.GenreRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.exception.AuthorNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public long countBooks() {
        return bookRepository.count();
    }

    @Override
    public Book insertBook(String bookName, long authorId, long genreId) {
        validateBookName(bookName);
        Author author = validateAuthorId(authorId);
        Genre genre = validateGenreId(genreId);
        Book book = new Book();
        book.setName(bookName);
        book.setGenre(genre);
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public List<Book> getBookByAuthorId(long authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get().getBooks();
        } else {
            throw new AuthorNotFoundException();
        }
    }

    @Override
    public List<Book> getBookByGenreId(long genreId) {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        if (optionalGenre.isPresent()) {
            return optionalGenre.get().getBooks();
        } else {
            throw new GenreNotFoundException();
        }
    }

    @Override
    public void deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(long id, String bookName, long authorId, long genreId) {
        validateBookName(bookName);
        Author author = validateAuthorId(authorId);
        Genre genre = validateGenreId(genreId);
        Book book = new Book(id, bookName, author, genre, Collections.emptyList());

        return bookRepository.save(book);
    }

    private void validateBookName(String bookName) {
        Assert.notNull(bookName, "Book name cannot be null");
        Assert.isTrue(Strings.isNotEmpty(bookName) || Strings.isNotBlank(bookName), "Book name cannot be empty");
    }

    private Author validateAuthorId(long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isEmpty()) {
            throw new AuthorNotFoundException();
        }
        return author.get();
    }

    private Genre validateGenreId(long genreId) {
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (genre.isEmpty()) {
            throw new GenreNotFoundException();
        }
        return genre.get();

    }

}
