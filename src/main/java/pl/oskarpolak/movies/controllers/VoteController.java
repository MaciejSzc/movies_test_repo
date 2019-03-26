package pl.oskarpolak.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.oskarpolak.movies.models.services.VoteService;

@Controller
public class VoteController {

    @Autowired
    VoteService voteService;

    @GetMapping("/vote/up/{movieId}")
    public String up(@PathVariable("movieId") int movieId){
        voteService.incrementUpVote(movieId);
        return "redirect:/movie/" + movieId;
    }

    @GetMapping("/vote/down/{movieId}")
    public String down(@PathVariable("movieId") int movieId){
        voteService.incrementDownVote(movieId);
        return "redirect:/movie/" + movieId;
    }


}
