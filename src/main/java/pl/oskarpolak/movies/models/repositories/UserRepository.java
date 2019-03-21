package pl.oskarpolak.movies.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.movies.models.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM `user` WHERE `username` = ?1 AND `password` = ?2")
    Optional<UserEntity> findUserByLoginAndPassword(String username, String password);
}
