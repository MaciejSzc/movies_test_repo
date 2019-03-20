package pl.oskarpolak.movies.models.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "author")
@Data
public class AuthorEntity {
    private @Id @GeneratedValue int id;
    private String name;
    private String surname;
    private LocalDate birthday;
}

