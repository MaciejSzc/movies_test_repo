package pl.oskarpolak.movies.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vote")
public class VoteEntity {
    private @Id @GeneratedValue int id;
    private @Column(name = "up_vote") int upVoteCount;
    private @Column(name = "down_vote") int downVoteCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "move_id")
    private MovieEntity movie;
}
