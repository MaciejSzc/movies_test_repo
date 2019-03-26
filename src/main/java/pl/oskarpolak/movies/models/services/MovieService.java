package pl.oskarpolak.movies.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.oskarpolak.movies.models.entities.AuthorEntity;
import pl.oskarpolak.movies.models.entities.MovieEntity;
import pl.oskarpolak.movies.models.entities.VoteEntity;
import pl.oskarpolak.movies.models.forms.MovieForm;
import pl.oskarpolak.movies.models.repositories.MovieRepository;
import pl.oskarpolak.movies.models.repositories.VoteRepository;

import java.nio.file.AtomicMoveNotSupportedException;
import java.util.Optional;

@Service
public class MovieService {
    public enum MovieResponse {
        CREATED, AUTHOR_NOT_EXIST, TITLE_ALREADY_EXIST;
    }


    @Autowired
    MovieRepository movieRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    VoteRepository voteRepository;




    public Iterable<MovieEntity> getAll(){
        return movieRepository.findAll();
    }


    @Transactional
    public MovieResponse addMovie(MovieForm movieForm){
        MovieEntity movieEntity = new MovieEntity();
        AuthorEntity authorEntity = authorService.findBySurname(movieForm.getAuthor());

        if(authorEntity == null){
            return MovieResponse.AUTHOR_NOT_EXIST;
        }

        if (movieRepository.existsByTitle(movieForm.getTitle())) {
            return MovieResponse.TITLE_ALREADY_EXIST;
        }

        movieEntity.setAuthor(authorEntity);
        movieEntity.setLongDescription(movieForm.getLongDescription());
        movieEntity.setShortDescription(movieForm.getShortDescription());
        movieEntity.setType(movieForm.getType());
        movieEntity.setYear(movieForm.getYear());
        movieEntity.setTitle(movieForm.getTitle());


        MovieEntity movieEntitySaved = movieRepository.save(movieEntity);

        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setMovie(movieEntitySaved);

        voteRepository.save(voteEntity);
        return MovieResponse.CREATED;
    }

    public MovieEntity getOneMovie(int id) {
        Optional<MovieEntity> optionalMovieEntity = movieRepository.findById(id);
        if (!optionalMovieEntity.isPresent()) {
            throw new IllegalStateException("Movie with that id, dont exist");
        }

        return optionalMovieEntity.get();
    }
}

