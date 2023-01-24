package ru.otus.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties("books")

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "genres")
@Table(name="GENRES")
public class Genre {
    @Id
    @SequenceGenerator(name = "genreIdGen", sequenceName = "GENRE_ID_SEQUENCE", initialValue = 6, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genreIdGen")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private List<Book> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
