package pl.oskarpolak.movies.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    private @Id @GeneratedValue int id;
    private String username;
    private @JsonIgnore String password;
    private String email;
    private @Column(name = "is_admin") boolean isAdmin;
}
