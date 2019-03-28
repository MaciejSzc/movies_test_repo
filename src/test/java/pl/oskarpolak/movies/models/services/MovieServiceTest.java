package pl.oskarpolak.movies.models.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.oskarpolak.movies.models.entities.AuthorEntity;
import pl.oskarpolak.movies.models.forms.MovieForm;
import pl.oskarpolak.movies.models.repositories.MovieRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class MovieServiceTest {

    @Mock
    AuthorService authorService;

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService movieService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void shouldNotAddFilmWithNotExistAuthor() {
        //given
        String authorName = "Pacek";

        MovieForm movieForm = new MovieForm();
        movieForm.setAuthor(authorName);

        //when
        Mockito.when(authorService.findBySurname(authorName)).thenReturn(null);

        //then
        Assertions.assertEquals(MovieService.MovieResponse.AUTHOR_NOT_EXIST, movieService.addMovie(movieForm));
    }

    @Test
    void shouldNotAddMovieWithAlreadyExistTitle() {
        //given
        String movieTitle = "test_title";

        MovieForm movieForm = new MovieForm();
        movieForm.setTitle(movieTitle);

        //when
        Mockito.when(authorService.findBySurname(null)).thenReturn(new AuthorEntity()); //STUB
        Mockito.when(movieRepository.existsByTitle(movieTitle)).thenReturn(true);

        //then
        Assertions.assertEquals(MovieService.MovieResponse.TITLE_ALREADY_EXIST, movieService.addMovie(movieForm));
    }

    @Test
    public void shouldGetOneMovieMethodThrowExceptionForNotExistingMovie(){
         //given
         int notExistingId = anyInt();

         //when
        Mockito.when(movieRepository.findById(notExistingId)).thenReturn(Optional.ofNullable(null));

        //then
        Assertions.assertThrows(IllegalStateException.class, () -> movieService.getOneMovie(notExistingId));
    }
}