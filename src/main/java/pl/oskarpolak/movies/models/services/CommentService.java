package pl.oskarpolak.movies.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.oskarpolak.movies.models.entities.CommentEntity;
import pl.oskarpolak.movies.models.entities.MovieEntity;
import pl.oskarpolak.movies.models.repositories.CommentRepository;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public void addComment(int movieId, String comment){
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(movieId);

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(comment);
        commentEntity.setMovie(movieEntity);

        commentRepository.save(commentEntity);
    }
}
