package pl.oskarpolak.movies.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.oskarpolak.movies.models.entities.AuthorEntity;
import pl.oskarpolak.movies.models.entities.MovieEntity;
import pl.oskarpolak.movies.models.forms.MovieForm;
import pl.oskarpolak.movies.models.repositories.MovieRepository;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    AuthorService authorService;


    public Iterable<MovieEntity> getAll(){
        return movieRepository.findAll();
    }

    public void addMovie(MovieForm movieForm){
        MovieEntity movieEntity = new MovieEntity();
        AuthorEntity authorEntity = authorService.findBySurname(movieForm.getAuthor());


        movieEntity.setAuthor(authorEntity);
        movieEntity.setLongDescription(movieForm.getLongDescription());
        movieEntity.setShortDescription(movieForm.getShortDescription());
        movieEntity.setType(movieForm.getType());
        movieEntity.setYear(movieForm.getYear());
        movieEntity.setTitle(movieForm.getTitle());

        movieRepository.save(movieEntity);
    }

    public MovieEntity getOneMovie(int id) {
        Optional<MovieEntity> optionalMovieEntity = movieRepository.findById(id);
        if (!optionalMovieEntity.isPresent()) {
            throw new IllegalStateException("Movie with that id, dont exist");
        }

        return optionalMovieEntity.get();
    }
}

