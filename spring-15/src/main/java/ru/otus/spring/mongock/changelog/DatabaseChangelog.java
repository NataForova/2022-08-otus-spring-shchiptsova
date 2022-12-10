package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import ru.otus.spring.dao.BookRepository;
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
    public void insertAuthors(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("authors");
        var doc = new Document()
                .append("1", "Joanne Rowling")
                .append("2", "Charles Dickens")
                .append("3", "Arthur Conan Doyle");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "NShchiptsova")
    public void insertGenres(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("genres");
        var doc = new Document()
                .append("1", "Fantasy")
                .append("2", "Comedy")
                .append("3", "Science fiction")
                .append("4", "Detective")
                .append("5", "Novel");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "004", id = "insertComments", author = "NShchiptsova")
    public void insertComments(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("comments");
        var doc = new Document()
                .append("1", "'Good Book, 1")
                .append("2", "I like it!, 1")
                .append("3", "I hate it, 2");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "005", id = "insertBooks", author = "NShchiptsova")
    public void insertBooks(BookRepository bookRepository) {
        List<Book> books = new ArrayList<>();

        Book sherlockHolmes = new Book(1L, "Sherlock Holmes",
                new Author(3L, "Arthur Conan Doyle"),
                new Genre(4L, "Detective"),
                Arrays.asList(new Comment(), new Comment()));
        books.add(sherlockHolmes);

        Book lostWorld = new Book(2L, "Lost World",
                new Author(3L, "Arthur Conan Doyle"),
                new Genre(3L, "Science fiction"),
                List.of(new Comment()));
        books.add(lostWorld);

        Book harryPotter = new Book(3L, "Harry Potter",
                new Author(3L, "Arthur Conan Doyle"),
                new Genre(3L, "Science fiction"),
               Collections.emptyList());
        books.add(harryPotter);

        Book casualVacancy = new Book(4L, "The Casual Vacancy",
                new Author(1L, "Joanne Rowling"),
                new Genre(2L, "Comedy"),
                Collections.emptyList());
        books.add(casualVacancy);

        Book oliverTwist = new Book(5L, "Oliver Twist",
                new Author(2L, "Charles Dickens"),
                new Genre(5L, "Novel"),
                Collections.emptyList());
        books.add(oliverTwist);

        Book mysteryOfEdwinDroods = new Book(6L, "The Mystery of Edwin Droods",
                new Author(2L, "Charles Dickens"),
                new Genre(5L, "Novel"),
                Collections.emptyList());
        books.add(mysteryOfEdwinDroods);

        Book davidCopperfield = new Book(7L, "David Copperfield",
                new Author(2L, "Charles Dickens"),
                new Genre(5L, "Novel"),
                Collections.emptyList());
        books.add(mysteryOfEdwinDroods);

        bookRepository.saveAll(books);
    }

}
