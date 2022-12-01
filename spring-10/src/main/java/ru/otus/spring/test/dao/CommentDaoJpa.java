package ru.otus.spring.test.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.otus.spring.test.domain.Comment;
import ru.otus.spring.test.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private final EntityManager em;

    public CommentDaoJpa(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public long count() {
        return em.createQuery("select count(c) " +
                        "from comments c",
                Long.class).getSingleResult();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Comment getById(long id) {
        Optional<Comment> optionalComment = Optional.ofNullable(em.find(Comment.class, id));
        return optionalComment.orElse(null);
    }

    @Override
    public List<Comment> getAll() {
        return em.createQuery("select c from comments c", Comment.class).getResultList();
    }

    @Override
    public Comment update(Comment comment) {
        return save(comment);
    }

    @Override
    public void deleteById(long id) {
        Comment comment = getById(id);
        if (comment != null) {
            em.remove(comment);
        }
    }
}
