package pl.oskarpolak.movies.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.oskarpolak.movies.models.entities.CommentEntity;
import pl.oskarpolak.movies.models.services.CommentService;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comment/add/{movieId}")
    public String comment(@PathVariable("movieId") int movieId,
                          @RequestParam("comment") String comment){
        commentService.addComment(movieId, comment);
        return "redirect:/movie/" + movieId;
    }

}
