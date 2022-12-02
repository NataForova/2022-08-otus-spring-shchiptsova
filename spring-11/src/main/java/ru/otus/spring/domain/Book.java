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
public class Book {

    @Id
    @SequenceGenerator(name = "bookIdGen", sequenceName = "BOOK_ID_SEQUENCE", initialValue = 8, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookIdGen")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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
