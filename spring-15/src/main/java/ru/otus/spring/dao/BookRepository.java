package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Long> {

    Optional<List<Book>> findAllByAuthor_Id(Long authorId);
    Optional<List<Book>> findAllByGenre_Id(Long genreId);
}
