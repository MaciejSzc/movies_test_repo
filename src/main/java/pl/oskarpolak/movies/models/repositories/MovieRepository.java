package pl.oskarpolak.movies.models.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.movies.models.entities.MovieEntity;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(`id`) > 0 THEN 'true' ELSE 'false' END FROM `movie` WHERE title = ?1")
    boolean existsByTitle(String title);


    //@Query(SELECT title FROM movie JOIN author ON movie.author_id = author.id WHERE author.surname = "polak")
    //MovieEntity findByAuthor_Surname(String surname);


}
