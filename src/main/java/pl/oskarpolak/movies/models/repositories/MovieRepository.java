package pl.oskarpolak.movies.models.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.movies.models.entities.MovieEntity;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Integer> {
}
