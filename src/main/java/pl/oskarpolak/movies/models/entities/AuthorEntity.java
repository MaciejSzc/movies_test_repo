package pl.oskarpolak.movies.models.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "author")
@Data
public class AuthorEntity {
    private @Id @GeneratedValue int id;
    private String name;
    private String surname;
    private LocalDate birthday;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY) //lazy i eager
    List<MovieEntity> movies;
}

