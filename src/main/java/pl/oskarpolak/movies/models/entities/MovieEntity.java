package pl.oskarpolak.movies.models.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "movie")
public class MovieEntity {
    private @Id @GeneratedValue int id;
    private String title;
    private int year;
    private  @Column(name = "short_description") String shortDescription;
    private  @Column(name = "long_description") String longDescription;
    private String type;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @OneToMany(mappedBy = "movie")
    List<CommentEntity> comments;
}
