package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findAllByAuthor_Id(Long authorId);
    Optional<List<Book>> findAllByGenre_Id(Long genreId);
}
