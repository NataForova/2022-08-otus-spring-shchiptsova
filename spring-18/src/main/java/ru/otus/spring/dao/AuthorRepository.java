package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    Author findByName(String name);
}
