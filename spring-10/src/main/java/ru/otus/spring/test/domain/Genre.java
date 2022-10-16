package ru.otus.spring.test.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
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

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
