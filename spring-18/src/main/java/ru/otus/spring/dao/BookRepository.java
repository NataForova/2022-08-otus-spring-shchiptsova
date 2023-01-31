package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {

}
