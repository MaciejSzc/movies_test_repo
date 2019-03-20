package pl.oskarpolak.movies.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.movies.models.entities.AuthorEntity;

@Repository
public interface AuthorRepository  extends CrudRepository<AuthorEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM `author` WHERE surname = ?1")
    AuthorEntity findAuthorBySurname(String sdfdsadassdsadsad);

}
