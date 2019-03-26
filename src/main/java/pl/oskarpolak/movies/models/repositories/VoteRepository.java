package pl.oskarpolak.movies.models.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.movies.models.entities.VoteEntity;

import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<VoteEntity, Integer> {

    @Query(nativeQuery = true, value = "UPDATE `vote` SET `up_vote` = `up_vote` + 1 WHERE `move_id` = ?1")
    @Modifying
    void incrementUpVote(int moveId);

    @Query(nativeQuery = true, value = "UPDATE `vote` SET `down_vote` = `down_vote` + 1 WHERE `move_id` = ?1")
    @Modifying
    void incrementDownVote(int moveId);

    @Query(nativeQuery = true, value = "SELECT * FROM `vote` WHERE `move_id` = ?1")
    Optional<VoteEntity> findVoteByMoveId(int movieId);
}
