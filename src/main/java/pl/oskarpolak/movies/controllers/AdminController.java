package pl.oskarpolak.movies.controllers;


import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.oskarpolak.movies.models.forms.MovieForm;
import pl.oskarpolak.movies.models.services.MovieService;
import pl.oskarpolak.movies.models.services.UserService;
import pl.oskarpolak.movies.models.services.UserSession;

@Controller
public class AdminController {
    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;

    @Autowired
    UserSession userSession;



    @GetMapping("/admin/add")
    public String movie(Model model){
        if(userSession.isLogin() && userService.findUserById(userSession.getUserId()).isAdmin()){
            model.addAttribute("movieForm", new MovieForm());
            return "movie_add";
        }

        return "redirect:/";
    }

    @PostMapping("/admin/add")
    public String movie(@ModelAttribute MovieForm movieForm,
                        Model model){
        if(userSession.isLogin() && userService.findUserById(userSession.getUserId()).isAdmin()){
            MovieService.MovieResponse movieResponse = movieService.addMovie(movieForm);
            if(movieResponse != MovieService.MovieResponse.CREATED){
                model.addAttribute("movieResponse", movieResponse);
                return "movie_add";
            }

        }
        return "redirect:/";
    }
}
