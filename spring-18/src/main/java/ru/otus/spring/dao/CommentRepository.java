package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
}
