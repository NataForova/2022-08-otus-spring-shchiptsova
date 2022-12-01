package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "books")
@Table(name = "BOOKS")
@NamedEntityGraph(name = "book-comments-entity-graph",
        attributeNodes = {@NamedAttributeNode("comments")})
public class Book {

    @Id
    @SequenceGenerator(name = "bookIdGen", sequenceName = "BOOK_ID_SEQUENCE", initialValue = 8, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookIdGen")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(targetEntity = Author.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne(targetEntity = Genre.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", comments=" + comments +
                '}' + '\n';
    }
}
