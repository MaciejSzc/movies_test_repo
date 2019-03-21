package pl.oskarpolak.movies.models.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Data
public class CommentEntity {
    private @Id @GeneratedValue int id;
    private String comment;
    private @Column(name = "creation_time") LocalDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
