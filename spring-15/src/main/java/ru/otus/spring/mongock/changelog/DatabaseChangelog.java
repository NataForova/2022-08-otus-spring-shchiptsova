package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import ru.otus.spring.dao.AuthorRepository;
import ru.otus.spring.dao.BookRepository;
import ru.otus.spring.dao.CommentRepository;
import ru.otus.spring.dao.GenreRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "NShchiptsova", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "NShchiptsova")
    public void insertAuthors(AuthorRepository authorRepository) {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("1", "Joanne Rowling"));
        authors.add(new Author("2", "Charles Dickens"));
        authors.add(new Author("3", "Arthur Conan Doyle"));
        authorRepository.saveAll(authors);
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "NShchiptsova")
    public void insertGenres(GenreRepository genreRepository) {
        List<Genre> genres = new ArrayList<>();

        genres.add(new Genre("1", "Fantasy"));
        genres.add(new Genre("2", "Comedy"));
        genres.add(new Genre("3", "Science fiction"));
        genres.add(new Genre("4", "Detective"));
        genres.add(new Genre("5", "Novel"));

        genreRepository.saveAll(genres);
    }

    @ChangeSet(order = "004", id = "insertComments", author = "NShchiptsova")
    public void insertComments(CommentRepository commentRepository) {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment("1", "1", "Good book"));
        comments.add(new Comment("2", "1", "I like it"));
        comments.add(new Comment("3", "2", "Not so good"));
        commentRepository.saveAll(comments);
    }

    @ChangeSet(order = "005", id = "insertBooks", author = "NShchiptsova")
    public void insertBooks(BookRepository bookRepository, CommentRepository commentRepository,
                            AuthorRepository authorRepository, GenreRepository genreRepository) {
        List<Book> books = new ArrayList<>();
        List<Comment> comments = commentRepository.findAll();
        List<Author> authors = authorRepository.findAll();
        List<Genre> genres = genreRepository.findAll();

        Book sherlockHolmes = new Book("1", "Sherlock Holmes",
                authors.get(2),
                genres.get(3),
                Arrays.asList(comments.get(0),
                        comments.get(1)));
        books.add(sherlockHolmes);

        Book lostWorld = new Book("2", "Lost World",
                authors.get(2),
                genres.get(2),
                List.of(comments.get(2)));
        books.add(lostWorld);

        Book harryPotter = new Book("3", "Harry Potter",
                authors.get(2),
                genres.get(2),
                Collections.emptyList());
        books.add(harryPotter);

        Book casualVacancy = new Book("4", "The Casual Vacancy",
                authors.get(0),
                genres.get(1),
                Collections.emptyList());
        books.add(casualVacancy);

        Book oliverTwist = new Book("5", "Oliver Twist",
                authors.get(1),
                genres.get(4),
                Collections.emptyList());
        books.add(oliverTwist);

        Book mysteryOfEdwinDroods = new Book("6", "The Mystery of Edwin Droods",
                authors.get(1),
                genres.get(4),
                Collections.emptyList());
        books.add(mysteryOfEdwinDroods);

        Book davidCopperfield = new Book("7", "David Copperfield",
                authors.get(1),
                genres.get(4),
                Collections.emptyList());
        books.add(davidCopperfield);

        bookRepository.saveAll(books);
    }

}
