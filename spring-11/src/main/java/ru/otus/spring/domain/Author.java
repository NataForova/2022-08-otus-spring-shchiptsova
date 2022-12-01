package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "authors")
@Table(name = "AUTHORS")
public class Author {
    @Id
    @SequenceGenerator(name = "authorIdGen", sequenceName = "AUTHOR_ID_SEQUENCE", initialValue = 4, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorIdGen")
    private Long id;
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
