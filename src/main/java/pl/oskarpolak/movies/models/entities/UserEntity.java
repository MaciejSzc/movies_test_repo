package pl.oskarpolak.movies.models.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    private @Id @GeneratedValue int id;
    private String username;
    private String password;
    private String email;
}
