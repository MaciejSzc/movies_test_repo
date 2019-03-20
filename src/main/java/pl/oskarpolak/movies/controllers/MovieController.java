package pl.oskarpolak.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.oskarpolak.movies.models.forms.MovieForm;
import pl.oskarpolak.movies.models.services.MovieService;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/")
    public String movie(Model model){
        model.addAttribute("movies", movieService.getAll());
        return "movie_all";
    }

    @GetMapping("/movie/{id}")
    public String movie(@PathVariable("id") int id,
                        Model model){
        model.addAttribute("oneMovie", movieService.getOneMovie(id));
        return "movie_one";
    }


}
