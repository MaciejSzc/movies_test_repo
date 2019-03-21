package pl.oskarpolak.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.oskarpolak.movies.models.services.MovieService;
import pl.oskarpolak.movies.models.services.UserSession;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    UserSession userSession;

    @GetMapping("/")
    public String movie(Model model){
        model.addAttribute("movies", movieService.getAll());
        model.addAttribute("userSession", userSession);

        return "movie_all";
    }

    @GetMapping("/movie/{id}")
    public String movie(@PathVariable("id") int id,
                        Model model){
        model.addAttribute("oneMovie", movieService.getOneMovie(id));
        return "movie_one";
    }


}
