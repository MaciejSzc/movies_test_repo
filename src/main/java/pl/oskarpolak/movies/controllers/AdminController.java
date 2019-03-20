package pl.oskarpolak.movies.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.oskarpolak.movies.models.forms.MovieForm;
import pl.oskarpolak.movies.models.services.MovieService;

@Controller
public class AdminController {
    @Autowired
    MovieService movieService;

    @GetMapping("/admin/add")
    public String movie(Model model){
        model.addAttribute("movieForm", new MovieForm());
        return "movie_add";
    }

    @PostMapping("/admin/add")
    public String movie(@ModelAttribute MovieForm movieForm){
        movieService.addMovie(movieForm);
        return "redirect:/";
    }
}
