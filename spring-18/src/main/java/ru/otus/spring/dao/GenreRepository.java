package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
    Genre findByName(String name);
}
